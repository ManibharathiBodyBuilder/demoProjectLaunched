package com.DemoProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DemoProject.Entity.SampleProduct;
@Repository
public interface SampleProductRepository extends JpaRepository<SampleProduct, Long> {
	
}
