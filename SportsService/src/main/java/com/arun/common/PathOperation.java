package com.arun.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public interface PathOperation<T> {

    Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value);

}
