package com.DemoProject.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DemoProject.Entity.ApiResponse;
import com.DemoProject.Entity.Product;
import com.DemoProject.Repository.ProductRepository;
import com.DemoProject.Scheduler.ProductScheduler;
import com.DemoProject.ServiceImpl.ProductServiceImple;
import com.DemoProject.Services.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@GetMapping("/all")
	public Map<String, Object> getAllData(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue="0")int size) {
		Map<String, Object> product = productService.getAllproduct(page, size);
		return product;
	}
	
	@GetMapping("{id}")
	public Product getAllData(@PathVariable("id") Long id ) {
		Product product = productService.findbyId(id);
		return product;
	}
	
	@DeleteMapping("{id}")
	public ApiResponse DeleteData(@PathVariable("id") Long id) {
		ApiResponse apiResponse = productService.DeleteByData(id);
		logger.debug("Deleted Data Triggered");
		return apiResponse;
	}
	
	@GetMapping("/filter")
	public List<Product> getAllData(@RequestParam(required = false) String category,
	                                @RequestParam(required = false) String keyword,
	                                @RequestParam(required = false) Double max,
	                                @RequestParam(required = false) Double min,
	                                @RequestParam(required = false) Double ratings) {
	    return productService.FilterProduct(category, keyword, max, min,ratings);
	}



	@PostMapping("/create")
	public ResponseEntity<ApiResponse> CreateProduct(@Valid @RequestBody Product product) {
		ResponseEntity<ApiResponse> productData = productService.saveProduct(product);
		logger.debug("Logging is triggered");
		return productData;
	}
	
	 @GetMapping("/export/products/excel")
	    public ResponseEntity<InputStreamResource> exportProductsToExcel() {
	        List<Product> products = productRepository.findAll();
	        ByteArrayInputStream in = ProductScheduler.exportToExcel(products);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=products.xlsx");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
	                .body(new InputStreamResource(in));
	    }

/*

	    @GetMapping("/export/products/excel")
	    public void exportToExcel(HttpServletResponse response) throws IOException {
	    	response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=products.xlsx";
	        response.setHeader(headerKey, headerValue);

	        List<Product> products = productRepository.findAll();

	        try (Workbook workbook = new XSSFWorkbook()) {
	            Sheet sheet = workbook.createSheet("Products");

	            Row headerRow = sheet.createRow(0);
	            String[] columns = {"ID", "Name", "Price", "Description", "Ratings", "Seller", "Stock", "NumOfReview", "Category"};

	            for (int i = 0; i < columns.length; i++) {
	                Cell cell = headerRow.createCell(i);
	                cell.setCellValue(columns[i]);
	                CellStyle style = workbook.createCellStyle();
	                Font font = workbook.createFont();
	                font.setBold(true);
	                style.setFont(font);
	                cell.setCellStyle(style);
	            }

	            int rowNum = 1;
	            for (Product p : products) {
	                Row row = sheet.createRow(rowNum++);
	                row.createCell(0).setCellValue(p.getId());
	                row.createCell(1).setCellValue(p.getName());
	                row.createCell(2).setCellValue(p.getPrice());
	                row.createCell(3).setCellValue(p.getDescription());
	                row.createCell(4).setCellValue(p.getRatings());
	                row.createCell(5).setCellValue(p.getSeller());
	                row.createCell(6).setCellValue(p.getStock());
	                row.createCell(7).setCellValue(p.getNumOfReview());
	                row.createCell(8).setCellValue(p.getCategory());
	            }

	            for (int i = 0; i < columns.length; i++) {
	                sheet.autoSizeColumn(i);
	            }

	            workbook.write(response.getOutputStream());
	        }*/
	    
	}

	

	


