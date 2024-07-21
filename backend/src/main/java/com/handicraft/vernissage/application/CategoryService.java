package com.handicraft.vernissage.application;

import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.domain.product.category.CategoryRepo;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryCreationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public void save(CategoryCreationRequest categoryCreationRequest) {
        categoryRepo.save(Category.of(
                categoryCreationRequest.name(),
                categoryCreationRequest.description(),
                categoryCreationRequest.parentId()
        ));
    }

    public List<CategoryBackofficeModel> all() {
        return categoryRepo.all().stream()
                .map(x -> new CategoryBackofficeModel(
                        x.id(),
                        x.name(),
                        x.description(),
                        x.parentId()
                ))
                .toList();
    }
}
