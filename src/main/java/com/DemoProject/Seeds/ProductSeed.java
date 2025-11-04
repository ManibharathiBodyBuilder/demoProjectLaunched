/*package com.DemoProject.Seeds;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.DemoProject.Entity.Product;
import com.DemoProject.Repository.ProductRepository;

@Component
public class ProductSeed implements CommandLineRunner {

	@Autowired
	private ProductRepository prodRepository;

	@Override
	public void run(String... args) throws Exception {
		if (prodRepository.count() == 0) {
			List<Product> products = Arrays.asList(
				    new Product(null, "Ravi", 250.20, "SmartPhone with 16-inch chip", 4.8, "Amazon", 8, "Electronics"),
				    new Product(null, "Sony Headphones", 99.99, "Noise-cancelling over-ear headphones", 4.5, "Flipkart", 12, "Audio"),
				    new Product(null, "Apple MacBook", 1299.00, "Laptop with M2 chip and 16GB RAM", 4.9, "Apple Store", 5, "Computers"),
				    new Product(null, "Samsung Galaxy S24", 799.50, "Latest 5G smartphone with AMOLED display", 4.7, "Croma", 10, "Mobiles"),
				    new Product(null, "LG 4K TV", 499.99, "55-inch UHD Smart LED TV", 4.4, "Reliance Digital", 7, "Electronics"),
				    new Product(null, "Nike Shoes", 79.99, "Comfortable running shoes for men", 4.3, "Nike Store", 20, "Fashion"),
				    new Product(null, "Levi's Jeans", 45.50, "Slim-fit denim jeans for men", 4.1, "Myntra", 15, "Fashion"),
				    new Product(null, "Canon EOS 1500D", 549.99, "DSLR camera with 18-55mm lens", 4.6, "Amazon", 9, "Cameras"),
				    new Product(null, "Mi Power Bank", 25.00, "10000mAh fast charging power bank", 4.2, "Mi Store", 25, "Accessories"),
				    new Product(null, "HP Printer", 159.99, "Wireless inkjet printer for home use", 4.0, "HP Store", 6, "Office Supplies"),
				    new Product(null, "Boat Smartwatch", 49.99, "Fitness tracker with heart rate monitor", 4.3, "Boat", 18, "Wearables"),
				    new Product(null, "Asus ROG Laptop", 1899.00, "Gaming laptop with RTX 4070 GPU", 4.8, "Asus Store", 4, "Computers"),
				    new Product(null, "Dell Monitor", 199.99, "27-inch Full HD monitor", 4.5, "Dell", 10, "Electronics"),
				    new Product(null, "Realme Buds", 29.99, "Wireless earbuds with noise cancellation", 4.1, "Realme", 30, "Audio"),
				    new Product(null, "Tupperware Bottle", 12.99, "Reusable BPA-free water bottle", 4.0, "Tupperware", 40, "Home & Kitchen")
				);


			prodRepository.saveAll(products);
			System.out.println("seeds created");
		} else {
			System.out.println("Seeds already exists");
		}

	}

}
*/