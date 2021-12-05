package com.example.ewears.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.junit.Ignore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="CART")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CART_ID", updatable = false, nullable = false)
    private String cartId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private double totalPrice;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, mappedBy = "cart")
    List<Product> allProducts;


}
