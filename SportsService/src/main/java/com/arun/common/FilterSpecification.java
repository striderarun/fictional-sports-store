package com.arun.common;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.MapUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * Filter Specification to build {@link Predicate} from the applied filter
 */
public class FilterSpecification<T> implements Specification<T> {

    private final Map<String, Map<PathOperation<?>, PathAndValue>> filteredPaths;

    public FilterSpecification(Map<String, Map<PathOperation<?>, PathAndValue>> filteredPaths) {
        this.filteredPaths = filteredPaths;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate conjunction = cb.conjunction();
        for (Entry<String, Map<PathOperation<?>, PathAndValue>> filteredPathsEntry : this.filteredPaths.entrySet()) {
            Map<PathOperation<?>, PathAndValue> filteredPath = filteredPathsEntry.getValue();
            if (MapUtils.isNotEmpty(filteredPath)) {
                Predicate disjunction = cb.disjunction();
                for (Entry<PathOperation<?>, PathAndValue> filteredPathEntry : filteredPath.entrySet()) {
                    PathOperation<?> pathOperation = filteredPathEntry.getKey();
                    PathAndValue pathAndValue = filteredPathEntry.getValue();
                    Predicate predicate = pathOperation.buildPredicate(cb, pathAndValue.getPath(root), pathAndValue.getValue());
                    disjunction = cb.or(disjunction, predicate);
                }
                conjunction = cb.and(disjunction, conjunction);
            }
        }
        return conjunction;
    }

}
