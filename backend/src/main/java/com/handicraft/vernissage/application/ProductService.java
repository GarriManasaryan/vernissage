package com.handicraft.vernissage.application;

import com.handicraft.vernissage.domain.product.ProductRepo;
import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.handicraft.vernissage.application.common.FeatureEntityMapperService.switchMapFeaturesToBackofficeModels;

@Service
@Transactional
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    private List<CategoryBackofficeModel> categoryBackofficeModels(List<Category> categories){
        return categories.stream().filter(Objects::nonNull).map(
                i -> new CategoryBackofficeModel(
                        i.id(),
                        i.name(),
                        i.description(),
                        i.parentId()
                )
        ).toList();

    }

    public List<ProductBackofficeModel> all() {
        System.out.println(productRepo.all());
        return productRepo.all().stream()
                .map(x -> new ProductBackofficeModel(
                        x.id(),
                        x.name(),
                        x.description(),
                        x.price(),
                        x.master(),
                        categoryBackofficeModels(x.categories()),
                        switchMapFeaturesToBackofficeModels(x.features())
                ))
                .toList();
    }
}
