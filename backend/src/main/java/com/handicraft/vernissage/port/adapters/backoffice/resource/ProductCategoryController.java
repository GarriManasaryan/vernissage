package com.handicraft.vernissage.port.adapters.backoffice.resource;

import com.handicraft.vernissage.application.CategoryService;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryCreationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductCategoryController {

    private final CategoryService categoryService;

    public ProductCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/api/product-categories")
    public void save(@RequestBody CategoryCreationRequest categoryCreationRequest) {
        categoryService.save(categoryCreationRequest);
    }

    @GetMapping("/api/product-categories")
    public List<CategoryBackofficeModel> all() {
        return categoryService.all();
    }
}
