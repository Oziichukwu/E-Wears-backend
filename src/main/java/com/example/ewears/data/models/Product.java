package com.example.ewears.data.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "PRODUCT_ID", updatable = false, nullable = false)
    private String productId;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "QUANTITY", nullable = false)
    private double price;

    @ManyToOne
    @JsonIgnore
    private Cart cart;


    @Override
    public String toString() {
        return "Product{" +
                ", quantity=" + quantity +
                ", price=" + price +
                ", productId='" + productId + '\'' +
                '}';
    }
}
