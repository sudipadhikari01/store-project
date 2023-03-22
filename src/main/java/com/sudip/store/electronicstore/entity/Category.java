package com.sudip.store.electronicstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private String categoryId;

    @Column(name = "category_title", length = 70, nullable = false)
    private String title;

    @Column(name = "category_description", length = 500)
    private String description;

    private String coverImage;

}
