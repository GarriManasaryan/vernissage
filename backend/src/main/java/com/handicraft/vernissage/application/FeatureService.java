package com.handicraft.vernissage.application;

import com.handicraft.vernissage.application.common.DBValueChecker;
import com.handicraft.vernissage.application.common.FeatureEntityMapperService;
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

    public void saveFeatureText(FeatureTextCreationRequest featureTextCreationRequest) {
        DBValueChecker.checkValueInTable(featureTextCreationRequest.name(), featureRepo);
        var featureText = FeatureText.of(
                featureTextCreationRequest.name(),
                featureTextCreationRequest.description(),
                featureTextCreationRequest.parentId(),
                categoryCreationRequestModels(featureTextCreationRequest.categories()),
                featureTextCreationRequest.value()
        );
        featureRepo.saveFeatureBase(featureText);
        featureRepo.saveFeatureText(featureText);
        if (!featureText.categories().isEmpty()){
            featureRepo.addCategoriesToFeature(featureText);
        }
    }

    public void saveFeatureNumeric(FeatureNumericCreationRequest featureNumericCreationRequest) {
        DBValueChecker.checkValueInTable(featureNumericCreationRequest.name(), featureRepo);
        var featureNumeric = FeatureNumeric.of(
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
        featureRepo.saveFeatureBase(featureNumeric);
        featureRepo.saveFeatureNumeric(featureNumeric);
        if (!featureNumeric.categories().isEmpty()){
            featureRepo.addCategoriesToFeature(featureNumeric);
        }
    }


}
