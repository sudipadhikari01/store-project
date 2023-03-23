package com.sudip.store.electronicstore.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private String id;

    private String title;

    private double originalPrice;

    @Column(length = 1000)
    private String description;

    private double discountedPrice;

    private int quantity;

    private Date addedDate;

    private boolean isLive;

    private boolean isStockAvailable;


}
