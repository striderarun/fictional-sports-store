package com.arun.common;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.Attribute;

import org.apache.commons.lang3.StringUtils;

/**
 * Filter paths
 */
public abstract class FilteredPaths<T> {

    private static final String SPACE = " ";
    public static final String NEGATION_PREFIX = "!";
    public static final String SEPARATOR = ",";
    public static final String SEARCH_STRING = "search";

    private final FilteredPathsBuilder<T> builder = new FilteredPathsBuilder<T>();

    public final Map<String, Map<List<Attribute<?, ?>>, PathOperation<?>>> getValue() {
        return this.builder.getFilterablePaths();
    }

    protected FilteredPathsBuilder<T> filter(Attribute<?, ?>... path) {
        return builder.filter(path);
    }

    protected FilteredPathsBuilder<T> filter(String key, Attribute<?, ?>... path) {
        return builder.filter(key, path);
    }

    /**
     * Returns path operation with like pattern for search
     * 
     * @return {@code PathOperation}
     */
    protected PathOperation<T> searchLikePathOperation() {
        PathOperation<T> pathOperation = new PathOperation<T>() {

            @SuppressWarnings("unchecked")
            @Override
            public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
                if (StringUtils.isNotBlank(value)) {
                    Iterable<String> values = Splitter.on(SPACE).omitEmptyStrings().trimResults().split(value);
                    Predicate disjunction = cb.disjunction();
                    for (String val : values) {
                        disjunction = cb.or(disjunction, cb.like(cb.lower((Path<String>) path), likePatternStr(val)));
                    }
                    return cb.and(disjunction);
                }
                return cb.and();
            }

        };
        return pathOperation;
    }

    /**
     * Returns path operation with like pattern
     * 
     * @return {@code PathOperation}
     */
    protected PathOperation<T> likePathOperation() {
        PathOperation<T> pathOperation = new PathOperation<T>() {

            @SuppressWarnings("unchecked")
            @Override
            public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
                if (StringUtils.isNotBlank(value)) {              	
                    return cb.like(cb.lower((Path<String>) path), likePatternStr(value));
                }
                return cb.and();
            }

        };
        return pathOperation;
    }
    
    /**
     * Returns path operation with equal condition
     * 
     * @return {@code PathOperation}
     */
    protected PathOperation<T> longEqualsPathOperation() {
        PathOperation<T> pathOperation = new PathOperation<T>() {

            @SuppressWarnings("unchecked")
            @Override
            public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
                if (StringUtils.isNotBlank(value) && path.getJavaType() == Long.class) {              	
                    return cb.equal((Path<String>) path, Long.valueOf(value));
                }
                return cb.and();
            }

        };
        return pathOperation;
    }

    /**
     * Method to get {@code PathOperation} for begin with operation
     * 
     * @return {@code PathOperation}
     */
    protected PathOperation<T> beginWithOperation() {
        PathOperation<T> pathOperation = new PathOperation<T>() {

            @SuppressWarnings("unchecked")
            @Override
            public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
                if (StringUtils.isNotBlank(value)) {
                    return cb.like(cb.lower((Path<String>) path), getBeginsWithPattern(value));
                }
                return cb.and();
            }
        };
        return pathOperation;
    }
    
    /**
     * Prepares the like pattern for a search string
     * 
     * @param word
     * @return like pattern for given input.
     *         For example input, 'Test' output would be '%test%'
     */
    protected static String likePatternStr(final String word) {
        return word == null ? "" : String.format("%%%s%%", word.toLowerCase());
    }

    /**
     * Prepares the begins with like pattern for a search string
     * 
     * @param word
     * @return begin with pattern for given input.
     *         For example input, 'Test' output would be 'test%'
     */
    protected static String getBeginsWithPattern(final String word) {
        return word == null ? "" : String.format("%s%%", word.toLowerCase());
    }

}
