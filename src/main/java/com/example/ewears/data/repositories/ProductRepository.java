package com.example.ewears.data.repositories;

import com.example.ewears.data.models.Cart;
import com.example.ewears.data.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {


}
