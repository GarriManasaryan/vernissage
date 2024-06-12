package com.handicraft.vernissage.application;

import com.handicraft.vernissage.domain.product.Product;
import com.handicraft.vernissage.domain.product.ProductRepo;
import com.handicraft.vernissage.domain.product.feature.*;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeatureService {

    private final FeatureBaseRepo featureBaseRepo;
    private final FeatureNumericRepo featureNumericRepo;
    private final FeatureTextRepo featureTextRepo;

    public FeatureService(FeatureBaseRepo featureBaseRepo, FeatureNumericRepo featureNumericRepo, FeatureTextRepo featureTextRepo) {
        this.featureBaseRepo = featureBaseRepo;
        this.featureNumericRepo = featureNumericRepo;
        this.featureTextRepo = featureTextRepo;
    }

    private FeatureBase saveFeatureBase(FeatureBaseInterface featureBaseInterface){
        // мы поставили констраинт, мол название не должно дублироваться
        // если дубли нашелся, то добавляем только в конкретные таблицы новое значение для пола, например
        var featureBase = FeatureBase.of(
                featureBaseInterface.name(),
                featureBaseInterface.description().orElse(null),
                featureBaseInterface.parentId().orElse(null),
                featureBaseInterface.categoryId(),
                FeatureDiscriminator.valueOf(featureBaseInterface.discriminator())
        );
        featureBaseRepo.save(featureBase);

        return featureBase;
    }

    @Transactional
    public void saveFeatureNumeric(FeatureNumericCreationRequest featureNumericCreationRequest) {
        var featureBase = saveFeatureBase(featureNumericCreationRequest);
        featureNumericRepo.save(new FeatureNumeric(
                featureBase.id(),
                featureNumericCreationRequest.from(),
                featureNumericCreationRequest.to(),
                featureNumericCreationRequest.unit(),
                featureNumericCreationRequest.lessThanText(),
                featureNumericCreationRequest.moreThanText()
        ));
    }

    @Transactional
    public void saveFeatureText(FeatureTextCreationRequest featureTextCreationRequest) {
        var featureBase = saveFeatureBase(featureTextCreationRequest);
        featureTextRepo.save(new FeatureText(
                featureBase.id(),
                featureTextCreationRequest.value()
        ));
    }

    public List<FeatureBaseBackofficeModel> allFeatureBases() {
        return featureBaseRepo.all().stream()
                .map(x -> new FeatureBaseBackofficeModel(
                        x.id(),
                        x.name(),
                        x.description(),
                        x.parentId(),
                        x.categoryId(),
                        x.discriminator()
                ))
                .toList();
    }

    public List<FeatureTextBackofficeModel> allFeatureTexts() {
        return featureTextRepo.all().stream()
                .map(x -> new FeatureTextBackofficeModel(
                        x.id(),
                        x.value()
                ))
                .toList();
    }

    public List<FeatureNumericBackofficeModel> allFeatureNumerics() {
        return featureNumericRepo.all().stream()
                .map(x -> new FeatureNumericBackofficeModel(
                        x.id(),
                        x.from(),
                        x.to(),
                        x.unit(),
                        x.lessThanText(),
                        x.moreThanText()
                ))
                .toList();
    }

    public Optional<FeatureTextBackofficeModel> featureTextOfId(String id) {
        return featureTextRepo.ofId(id)
                .map(x -> new FeatureTextBackofficeModel(
                        x.id(),
                        x.value()
                ));
    }
}
