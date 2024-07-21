package com.handicraft.vernissage.port.adapters.backoffice.resource;

import com.handicraft.vernissage.application.FeatureService;
import com.handicraft.vernissage.domain.product.feature.FeatureNumeric;
import com.handicraft.vernissage.domain.product.feature.FeatureText;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureBaseBackofficeModelInterface;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.FeatureCreationRequestInterface;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.FeatureNumericCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.FeatureTextCreationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FeatureController {

    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @PostMapping("/api/features")
    public <T extends FeatureCreationRequestInterface> void saveFeature(@RequestBody T featureCreationRequest) {
        switch (featureCreationRequest){
            case FeatureTextCreationRequest featureText:
                featureService.saveFeatureText(featureText);
                break;

            case FeatureNumericCreationRequest featureNumeric:
                featureService.saveFeatureNumeric(featureNumeric);
                break;
        }
    }

    @GetMapping("/api/features")
    public List<? extends FeatureBaseBackofficeModelInterface> all() {
        return featureService.all();
    }

}
