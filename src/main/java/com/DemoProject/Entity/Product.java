package com.DemoProject.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "name field is required")
	private String name;
	@Column(nullable = false)
	@NotNull(message = "price field is required")
	@PositiveOrZero(message = "price must be zero or greater then zero")
	private Double price;
	@Column(nullable = false)
	@NotBlank(message = "Description field is required")
	private String description;
	private Double ratings = 0.0;
	@NotBlank(message = "seller field is required")
	private String seller;
	@NotNull(message = "stock field is required")
	private Integer stock;
	private Integer numOfReview = 0;
	private String category;
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private List<ProductImage> image;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private List<ProductReview> review;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, Double price, String description, Double ratings, String seller,
			Integer stock,String category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.ratings = ratings;
		this.seller = seller;
		this.stock = stock;
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRatings() {
		return ratings;
	}

	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getNumOfReview() {
		return numOfReview;
	}

	public void setNumOfReview(Integer numOfReview) {
		this.numOfReview = numOfReview;
	}

}
