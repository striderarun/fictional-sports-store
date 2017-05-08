package com.arun.common;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.Attribute;

import org.apache.commons.collections.MapUtils;

/**
 * Filter path builder
 */
public class FilteredPathsBuilder<T> {

    private final Map<String, Map<List<Attribute<?, ?>>, PathOperation<?>>> filterablePaths = Maps.newHashMap();

    private String lastKey;
    private List<Attribute<?, ?>> lastPath;
    private PathOperation<T> lastOp;

    public FilteredPathsBuilder<T> filter(String key, Attribute<?, ?>... paths) {
        updateFilterablePaths();
        this.lastKey = key;
        this.lastPath = Collections.unmodifiableList(Arrays.asList(paths));
        this.lastOp = null;
        return this;
    }

    public FilteredPathsBuilder<T> filter(Attribute<?, ?>... paths) {
        String key = getKey(paths);
        return this.filter(key, paths);
    }

    public FilteredPathsBuilder<T> with(PathOperation<T> op) {
        this.lastOp = op;
        return this;
    }

    public Map<String, Map<List<Attribute<?, ?>>, PathOperation<?>>> getFilterablePaths() {
        updateFilterablePaths();
        this.lastPath = null;
        this.lastOp = null;
        return Collections.unmodifiableMap(filterablePaths);
    }

    private void updateFilterablePaths() {
        if (lastKey != null && lastPath != null && lastOp != null) {
            Map<List<Attribute<?, ?>>, PathOperation<?>> filterablePathsForKey = filterablePaths.get(lastKey);
            if (MapUtils.isEmpty(filterablePathsForKey)) {
                filterablePathsForKey = Maps.newHashMap();
            }
            filterablePathsForKey.put(lastPath, lastOp);
            filterablePaths.put(lastKey, filterablePathsForKey);
        }
    }

    private String getKey(Attribute<?, ?>[] paths) {
        List<String> paramNames = Lists.newArrayList();
        for (Attribute<?, ?> singAttr : paths) {
            paramNames.add(singAttr.getName());
        }
        return Joiner.on(".").join(paramNames);
    }

}
