package com.sudip.store.electronicstore.dtos;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private String categoryId;

    @NotBlank(message = "title cannot be blank")
    private String title;

    @Min(value = 3, message = "description should of minimum length 3")
    private String description;


    private String coverImage;

}
