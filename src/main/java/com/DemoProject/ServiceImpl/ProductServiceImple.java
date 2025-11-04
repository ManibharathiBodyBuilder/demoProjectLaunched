package com.DemoProject.ServiceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DemoProject.Entity.ApiResponse;
import com.DemoProject.Entity.Product;
import com.DemoProject.Repository.ProductRepository;
import com.DemoProject.Services.ProductService;
import com.DemoProject.Specification.ProductSpecification;

@Service
public class ProductServiceImple implements ProductService {

	@Autowired
	private ProductRepository prodRep;
	
	 private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	public Map<String, Object> getAllproduct(int page, int size) {
		Pageable pagable = PageRequest.of(page, size);
		Page<Product> product = prodRep.findAll(pagable);
		Map<String, Object> products = new HashMap<>();
		products.put("products", product.getContent());
		products.put("TotalProducts", product.getTotalElements());
		return products;
	}
	
	@Cacheable(value = "products", key = "#id")
	public Product findbyId(Long id) {
	    return prodRep.findById(id)
	            .orElseThrow(() -> new RuntimeException("Id not found: " + id));
	}
	
	@CacheEvict(value = "products", key = "#id")
	public ApiResponse DeleteByData(Long id) {
		prodRep.deleteById(id);
		ApiResponse response = new ApiResponse("Data Deleted successfully", id, 200);
		return response;
	}


	
	public List<Product> FilterProduct(String category, String keyword, Double max, Double min,Double ratings) {
		Specification<Product> spec = Specification.where(null);

		if (category != null && !category.isEmpty()) {
			spec = spec.and(ProductSpecification.hashSpecfic(category));
		}
		if (keyword != null && !keyword.isEmpty()) {
			spec = spec.and(ProductSpecification.hasNameOrDescription(keyword));
		}
		if (min != null || max != null) {
			spec = spec.and(ProductSpecification.hasMaxAndMinPrice(max, min));
		}
		
		if (ratings != null) {
			spec = spec.and(ProductSpecification.hasRating(ratings));
		}

		return prodRep.findAll(spec);
	}

	
    @Transactional
    @CachePut(value = "products", key = "#id")
	 public ResponseEntity<ApiResponse> saveProduct(Product entity) {
    	 try {
    		 logger.info("Attempting to save product: {}", entity.getName());
 			Product productData = prodRep.save(entity);
 			logger.info("Attempting to save product: {}", entity.getName());
 			ApiResponse response = new ApiResponse("Product created successfully", productData, 201);
 			return new ResponseEntity<>(response, HttpStatus.CREATED);
 		} catch (Exception e) {
 			ApiResponse response = new ApiResponse("Error while creating product: " + e.getMessage(), null, 500);
 			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 		}
	          
               
        }
    }
