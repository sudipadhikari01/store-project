package com.sudip.store.electronicstore.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String id;

    private String title;

    private String description;

    private double originalPrice;

    private double discountedPrice;

    private int quantity;

    private Date addedDate;

    private boolean isLive;

    private boolean isStockAvailable;
}
