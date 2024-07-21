//package com.handicraft.vernissage.application;
//
//import com.handicraft.vernissage.domain.product.DeprecatedProductFeature;
//import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductFeatureBackofficeModel;
//import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductFeatureCreationRequest;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//public class ProductFeatureService {
//
//    private final ProductFeatureRepo productFeatureRepo;
//
//    public ProductFeatureService(ProductFeatureRepo productFeatureRepo) {
//        this.productFeatureRepo = productFeatureRepo;
//    }
//
//    public void save(@NotNull ProductFeatureCreationRequest productFeatureCreationRequest) {
//        // тут по двум ключам лень и гемор это делать,
//        // возьмем по названию констраинта на фронте ошибку и там обработаем
//        productFeatureRepo.save(new DeprecatedProductFeature(
//                productFeatureCreationRequest.productId(),
//                productFeatureCreationRequest.featureId()
//        ));
//    }
//
//    public List<ProductFeatureBackofficeModel> all() {
//        return productFeatureRepo.all().stream()
//                .map(x -> new ProductFeatureBackofficeModel(
//                        x.productId(),
//                        x.featureId()
//                ))
//                .toList();
//    }
//
//    public List<ProductFeatureBackofficeModel> getProductFeatures(@NotNull String productId) {
//        return productFeatureRepo.getProductFeatures(productId).stream()
//                .map(x -> new ProductFeatureBackofficeModel(
//                        x.productId(),
//                        x.featureId()
//                ))
//                .toList();
//    }
//}
//
//
