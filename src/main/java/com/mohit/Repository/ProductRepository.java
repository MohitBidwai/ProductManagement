package com.mohit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.models.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
