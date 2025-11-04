package com.DemoProject.Scheduler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.DemoProject.Entity.Product;
import com.DemoProject.Entity.SampleProduct;
import com.DemoProject.Repository.ProductRepository;
import com.DemoProject.Repository.SampleProductRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProductScheduler {

	private static final Logger logger = LoggerFactory.getLogger(ProductScheduler.class);

	@Autowired
	private ProductRepository prodRepository;

	@Autowired
	private SampleProductRepository sampleProdRepository;

	private boolean completed = false; // ✅ stop flag after all data inserted

	@Scheduled(fixedRate = 5000) // ✅ runs every 5 seconds
	public void autoInsertProduct() {
		try {
			if (completed) {
				return; // Stop execution once done
			}

			logger.info("Cron job started at {}", LocalDateTime.now());

			List<SampleProduct> sampleList = sampleProdRepository.findAll();
			long totalProducts = prodRepository.count();

			// ✅ Stop cron if all sample products already inserted
			if (totalProducts >= sampleList.size()) {
				logger.info("✅ All {} products already inserted. Stopping cron.", totalProducts);
				completed = true;
				return;
			}

			int insertedCount = 0;

			for (SampleProduct s : sampleList) {
				// ✅ Skip if product already exists (based on name)
				if (prodRepository.existsByName(s.getName())) {
					continue;
				}

				if (insertedCount >= 5)
					break; // ✅ Insert only 5 records per run

				Product product = new Product();
				product.setName(s.getName());
				product.setPrice(s.getPrice());
				product.setDescription(s.getDescription());
				product.setCategory(s.getCategory());
				product.setStock(s.getStock());
				product.setSeller(s.getSeller());
				product.setRatings(s.getRatings());
				product.setNumOfReview(s.getNumOfReview());

				prodRepository.save(product);
				insertedCount++;
				logger.info("✅ Inserted product: {}", product.getName());
			}

			logger.info("Inserted {} new products this round.", insertedCount);

		} catch (Exception e) {
			logger.error("❌ Error during auto insert: {}", e.getMessage());
		}
		
	}
	
	
		
		 public static ByteArrayInputStream exportToExcel(List<Product> products) {
	            try (Workbook workbook = new XSSFWorkbook();
	                 ByteArrayOutputStream out = new ByteArrayOutputStream()) {

	                Sheet sheet = workbook.createSheet("Products");

	                // Header row
	                Row header = sheet.createRow(0);
	                String[] columns = {"ID", "Name", "Category", "Description", "Price", "Stock", "Seller", "Ratings"};
	                for (int i = 0; i < columns.length; i++) {
	                    Cell cell = header.createCell(i);
	                    cell.setCellValue(columns[i]);
	                }

	                // Data rows
	                int rowIdx = 1;
	                for (Product product : products) {
	                    Row row = sheet.createRow(rowIdx++);
	                    row.createCell(0).setCellValue(product.getId());
	                    row.createCell(1).setCellValue(product.getName());
	                    row.createCell(2).setCellValue(product.getCategory());
	                    row.createCell(3).setCellValue(product.getDescription());
	                    row.createCell(4).setCellValue(product.getPrice());
	                    row.createCell(5).setCellValue(product.getStock());
	                    row.createCell(6).setCellValue(product.getSeller());
	                    row.createCell(7).setCellValue(product.getRatings());
	                }

	                workbook.write(out);
	                return new ByteArrayInputStream(out.toByteArray());

	            } catch (Exception e) {
	                e.printStackTrace();
	                return null;
	            }
	       
	}
}
