package com.DemoProject.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.DemoProject.Entity.Product;

public class ProductSpecification {
	public static Specification<Product> hashSpecfic(String category) {
		return (root, quary, cb) -> category == null ? null : cb.equal(root.get("category"), category);
	}

	public static Specification<Product> hasNameOrDescription(String keyword) {
		return (root, query, cb) -> cb.or(cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
				cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%"));
	}

	public static Specification<Product> hasMaxAndMinPrice(Double max, Double min) {
		return (root, query, cb) -> {
			if (min == null && max == null)
				return null;
			if (min == null)
				return cb.lessThanOrEqualTo(root.get("price"), max); // Use max
			if (max == null)
				return cb.greaterThanOrEqualTo(root.get("price"), min); // Use min
			return cb.between(root.get("price"), min, max);
		};
	}

	public static Specification<Product> hasRating(Double ratings) {
		return (root, query, cb) -> {
			if (ratings == null) {
		        return null; // null return panna spec.and(null) ignore pannum
		    }
			return cb.greaterThanOrEqualTo(root.get("ratings"), ratings);
		};
	}

}
