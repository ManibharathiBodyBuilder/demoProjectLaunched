package com.DemoProject.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.persistence.Id;

@Entity
public class ProductReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Max(value = 5)
	@Min(value = 1)
	private Integer rating;
	private String commend;
	
	public Long getId() {
		return Id;
	}
	public ProductReview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setId(Long id) {
		Id = id;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getCommend() {
		return commend;
	}
	public ProductReview(Long id, @Max(5) @Min(1) Integer rating, String commend) {
		super();
		Id = id;
		this.rating = rating;
		this.commend = commend;
	}
	public void setCommend(String commend) {
		this.commend = commend;
	}


}
