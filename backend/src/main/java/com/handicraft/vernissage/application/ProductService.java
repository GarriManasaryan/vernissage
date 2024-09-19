package com.handicraft.vernissage.application;

import com.handicraft.vernissage.domain.product.Product;
import com.handicraft.vernissage.domain.product.ProductRepo;
import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.domain.product.feature.FeatureNumeric;
import com.handicraft.vernissage.domain.product.feature.FeatureText;
import com.handicraft.vernissage.domain.product.price.Price;
import com.handicraft.vernissage.domain.user.Master;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureNumericBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureTextBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.price.PriceBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.user.MasterBackofficeModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.handicraft.vernissage.application.common.FeatureEntityMapperService.*;

@Service
@Transactional
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    private List<CategoryBackofficeModel> categoryBackofficeModels(List<Category> categories) {
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
        return productRepo.all().stream()
                .map(x -> new ProductBackofficeModel(
                        x.id(),
                        x.name(),
                        x.description().orElse(null),
                        new PriceBackofficeModel(
                                x.price().value(),
                                x.price().currency(),
                                x.price().discount().orElse(null)
                        ),
                        new MasterBackofficeModel(
                                x.master().id(),
                                x.master().name(),
                                x.master().role(),
                                x.master().timeZone()
                        ),
                        categoryBackofficeModels(x.categories()),
                        switchMapFeaturesToBackofficeModels(x.features())
                ))
                .toList();
    }

    public void save(ProductCreationRequest productCreationRequest) {
        productRepo.save(Product.of(
                productCreationRequest.name(),
                productCreationRequest.description(),
                new Price(
                        productCreationRequest.price().value(),
                        productCreationRequest.price().currency(),
                        Optional.ofNullable(productCreationRequest.price().discount())
                ),
                new Master(
                        productCreationRequest.master().id(),
                        productCreationRequest.master().name(),
                        productCreationRequest.master().role(),
                        productCreationRequest.master().timeZone()
                ),
                productCreationRequest.categories().stream().map(x -> new Category(
                        x.id(),
                        x.name(),
                        x.description(),
                        x.parentId()
                )).toList(),
                productCreationRequest.features().stream().map(feature -> switch (feature) {
                            case FeatureNumericBackofficeModel featureNumericBackofficeModel -> new FeatureNumeric(
                                    featureNumericBackofficeModel.id(),
                                    featureNumericBackofficeModel.name(),
                                    Optional.ofNullable(featureNumericBackofficeModel.description()),
                                    Optional.ofNullable(featureNumericBackofficeModel.parentId()),
                                    mapCategoriesList(featureNumericBackofficeModel.categories(), toCategory),
                                    featureNumericBackofficeModel.from(),
                                    Optional.ofNullable(featureNumericBackofficeModel.to()),
                                    Optional.ofNullable(featureNumericBackofficeModel.unit()),
                                    Optional.ofNullable(featureNumericBackofficeModel.lessThanText()),
                                    Optional.ofNullable(featureNumericBackofficeModel.moreThanText())
                            );
                            case FeatureTextBackofficeModel featureTextBackofficeModel -> new FeatureText(
                                    featureTextBackofficeModel.id(),
                                    featureTextBackofficeModel.name(),
                                    Optional.ofNullable(featureTextBackofficeModel.description()),
                                    Optional.ofNullable(featureTextBackofficeModel.parentId()),
                                    mapCategoriesList(featureTextBackofficeModel.categories(), toCategory),
                                    featureTextBackofficeModel.value()
                            );
                        }
                ).toList()
        ));
    }
}
