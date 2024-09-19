package com.handicraft.vernissage.port.adapters.backoffice.resource;

import com.handicraft.vernissage.application.FeatureService;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureBaseBackofficeModelInterface;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.FeatureCreationRequestInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FeatureController {

    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

//    @PostMapping("/api/features")
//    public <T extends FeatureCreationRequestInterface> void saveFeature(@RequestBody T featureCreationRequest) {
//        switch (featureCreationRequest){
//            case FeatureTextCreationRequest featureText:
//                featureService.saveFeatureText(featureText);
//                break;
//
//            case FeatureNumericCreationRequest featureNumeric:
//                featureService.saveFeatureNumeric(featureNumeric);
//                break;
//        }
//    }

    @PostMapping("/api/features")
    public void saveFeature(@RequestBody FeatureCreationRequestInterface featureCreationRequest) {
        featureService.saveFeature(featureCreationRequest);
    }

    @GetMapping("/api/features")
    public List<? extends FeatureBaseBackofficeModelInterface> all() {
        return featureService.all();
    }

}
