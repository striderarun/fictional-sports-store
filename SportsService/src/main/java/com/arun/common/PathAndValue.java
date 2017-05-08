package com.arun.common;

import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

public class PathAndValue {

    private final List<Attribute<?, ?>> path;
    private final String value;

    public PathAndValue(List<Attribute<?, ?>> path, String value) {
        this.path = path;
        this.value = value;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> Path<?> getPath(final Root<T> root) {
        Path<?> rv = root;
        for (int i = 0; i < path.size(); i++) {
            Attribute attribute = path.get(i);
            if (attribute instanceof SingularAttribute) {
                rv = rv.get((SingularAttribute) attribute);
            } else if (attribute instanceof SetAttribute) {
                rv = root.join((SetAttribute) attribute, JoinType.LEFT).get((SingularAttribute) path.get(++i));
            }
        }
        return rv;
    }

    public String getValue() {
        return value;
    }

}
