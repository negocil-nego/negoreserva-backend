package com.negoreserva.common.feature.concrete.category.component;

import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.concrete.category.service.CategoryService;
import com.negoreserva.common.feature.concrete.category.enums.CategoryData;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategorySeeder {
    private final CategoryService categoryService;

    public CategorySeeder(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Transactional
    public List<Category> seed() {
        List<Category> items = new ArrayList<>();
        for (CategoryData categoria : CategoryData.values()) {
            items.add(categoryService.findOrCreate(categoria.getCategory()));
        }
        return items;
    }

}