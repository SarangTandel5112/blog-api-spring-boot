package com.codewithdurgesh.blog.blogappapis.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    private String categoryTitle;

    @NotEmpty
    private String categoryDescription;

}
