package com.example.ewears.data.repositories;

import com.example.ewears.data.models.Cart;
import com.example.ewears.data.models.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    @Modifying
    @Transactional
    void deleteByProductId(String productId);

    Optional<Product> findByProductId(String productId);
}
