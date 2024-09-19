package com.handicraft.vernissage.application;

import com.handicraft.vernissage.application.common.RepoValueChecker;
import com.handicraft.vernissage.application.common.FeatureEntityMapperService;
import com.handicraft.vernissage.domain.product.feature.FeatureBase;
import com.handicraft.vernissage.domain.product.feature.FeatureNumeric;
import com.handicraft.vernissage.domain.product.feature.FeatureRepo;
import com.handicraft.vernissage.domain.product.feature.FeatureText;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureBaseBackofficeModelInterface;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.FeatureCreationRequestInterface;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.FeatureNumericCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.FeatureTextCreationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.handicraft.vernissage.application.common.FeatureEntityMapperService.categoryCreationRequestModels;
import static com.handicraft.vernissage.application.common.FeatureEntityMapperService.switchMapFeaturesToBackofficeModels;

@Service
@Transactional
public class FeatureService {


    private final FeatureRepo featureRepo;
    private final FeatureEntityMapperService featureEntityMapperService;

    public FeatureService(FeatureRepo featureRepo, FeatureEntityMapperService featureEntityMapperService) {
        this.featureRepo = featureRepo;
        this.featureEntityMapperService = featureEntityMapperService;
    }

    public List<? extends FeatureBaseBackofficeModelInterface> all() {
        return switchMapFeaturesToBackofficeModels(featureRepo.all());
    }

    private static FeatureBase mapToFeatureEntity(FeatureCreationRequestInterface featureCreationRequest){
        return switch (featureCreationRequest){
            case FeatureTextCreationRequest featureTextCreationRequest -> FeatureText.of(
                    featureTextCreationRequest.name(),
                    featureTextCreationRequest.description(),
                    featureTextCreationRequest.parentId(),
                    categoryCreationRequestModels(featureTextCreationRequest.categories()),
                    featureTextCreationRequest.value()
            );
            case FeatureNumericCreationRequest featureNumericCreationRequest -> FeatureNumeric.of(
                    featureNumericCreationRequest.name(),
                    featureNumericCreationRequest.description(),
                    featureNumericCreationRequest.parentId(),
                    categoryCreationRequestModels(
                            featureNumericCreationRequest.categories()
                    ),
                    featureNumericCreationRequest.from(),
                    featureNumericCreationRequest.to(),
                    featureNumericCreationRequest.unit(),
                    featureNumericCreationRequest.lessThanText(),
                    featureNumericCreationRequest.moreThanText()
            );
        };

    }

    public void saveFeature(FeatureCreationRequestInterface featureCreationRequest){
        var featureEntity = mapToFeatureEntity(featureCreationRequest);
        saveGenericFeature(featureEntity);

    }

    private <T extends FeatureBase> void saveGenericFeature(T featureEntity){
        // в аргументы Function<T, R> featureCreatorFunc, а внизу ее просто вызвать
        // R featureEntity = featureCreatorFunc.apply(t);
        // если надо будет в других примерах
        RepoValueChecker.checkValueInTable(featureEntity.name(), featureRepo);
        featureRepo.saveFeatureBase(featureEntity);
        switch (featureEntity){
            case FeatureNumeric featureNumeric -> featureRepo.saveFeatureNumeric(featureNumeric);
            case FeatureText featureText -> featureRepo.saveFeatureText(featureText);
        }
        if (!featureEntity.categories().isEmpty()){
            featureRepo.addCategoriesToFeature(featureEntity);
        }
    }


}
