package com.arun.specification;

import com.arun.common.PathOperation;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import com.arun.common.FilteredPaths;
import com.arun.domain.Shoes;
import com.arun.domain.Shoes_;

import javax.persistence.criteria.Predicate;
import java.util.List;

public class ShoeSpecification extends FilteredPaths<Shoes> {

	public ShoeSpecification() {
        filter(Shoes_.shoeName).with(likePathOperation());
        filter(Shoes_.brandName).with(likePathOperation());
        filter(Shoes_.price).with(longEqualsPathOperation());
	}
	
	public static Specification<Shoes> brandSpecification(final String brandName) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Shoes_.brandName), brandName);
		};

	}
	
	public static Specification<Shoes> priceSpecification(final Long price) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Shoes_.price), price);
		};
	}

	private PathOperation<Shoes> brandPathOperation() {
		return (cb, path, value) -> {
            if (StringUtils.isBlank(value)) {
                return cb.and();
            }
            List<Predicate> predicates = Lists.newArrayList();
            Iterable<String> brands = Splitter.on(SEPARATOR).split(value);
            List<String> brandsIn = Lists.newArrayList();
            for (String brand : brands) {
                if (StringUtils.isNotBlank(brand)) {
                    brandsIn.add(brand);
                }
            }
            if (CollectionUtils.isNotEmpty(brandsIn)) {
                predicates.add(path.in(brandsIn));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
	}
	
}
