package com.arun.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Filter definition annotation for controller parameters to build specification.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface FilterDefinition {

    @SuppressWarnings("rawtypes")
    Class<? extends FilteredPaths> paths();

    @SuppressWarnings("rawtypes")
    Class<? extends FilterSpecification> implementation() default FilterSpecification.class;

}
