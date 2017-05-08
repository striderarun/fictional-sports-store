package com.arun.common;


import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.metamodel.Attribute;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Argument resolver for parameters annotated with {@link FilterDefinition} annotation & of type {@link Specification}
 */
public class FilterDefinitionArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(FilterDefinition.class) && parameter.getParameterType() == Specification.class;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
            ) throws Exception {
        final FilterDefinition filterDefinition = parameter.getParameterAnnotation(FilterDefinition.class);
        final FilteredPaths<?> paths = filterDefinition.paths().newInstance();

        final Map<String, Map<PathOperation<?>, PathAndValue>> filterValues = Maps.newHashMap();
        Map<String, Map<List<Attribute<?, ?>>, PathOperation<?>>> filterablePaths = paths.getValue();
        for (Entry<String, Map<List<Attribute<?, ?>>, PathOperation<?>>> predicateKeyEntry : filterablePaths.entrySet()) {
            String param = predicateKeyEntry.getKey();
            String paramValue = webRequest.getParameter(param);
            if (StringUtils.isNotBlank(paramValue)) {
                Map<List<Attribute<?, ?>>, PathOperation<?>> pathOperationMap = predicateKeyEntry.getValue();
                final Map<PathOperation<?>, PathAndValue> filterMap = Maps.newHashMap();
                for (Entry<List<Attribute<?, ?>>, PathOperation<?>> pathOpEntry : pathOperationMap.entrySet()) {
                    filterMap.put(pathOpEntry.getValue(), new PathAndValue(pathOpEntry.getKey(), paramValue));
                }
                filterValues.put(param, filterMap);
            }
        }
        return filterDefinition.implementation().getConstructor(Map.class).newInstance(filterValues);
    }

   

}
