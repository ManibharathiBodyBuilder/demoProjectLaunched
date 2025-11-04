package com.DemoProject.Services;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.DemoProject.Entity.ApiResponse;
import com.DemoProject.Entity.Product;

public interface ProductService {
	public  Map<String, Object> getAllproduct(int page,int size);
	 public ResponseEntity<ApiResponse> saveProduct(Product entity);
	public Product findbyId(Long id);
	public ApiResponse DeleteByData(Long id);
	public List<Product> FilterProduct(String category, String keyword, Double max, Double min,Double ratings);

}
