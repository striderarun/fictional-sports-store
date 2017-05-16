package com.arun.specification;

import org.springframework.data.jpa.domain.Specification;
import com.arun.common.FilteredPaths;
import com.arun.domain.Shoes;
import com.arun.domain.Shoes_;

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
	
}
