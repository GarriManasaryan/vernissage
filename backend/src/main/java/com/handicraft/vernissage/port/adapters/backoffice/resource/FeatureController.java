package com.handicraft.vernissage.port.adapters.backoffice.resource;

import com.handicraft.vernissage.application.FeatureService;
import com.handicraft.vernissage.application.ProductService;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class FeatureController {

    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @PostMapping("/api/text-features")
    public void saveFeatureText(@RequestBody FeatureTextCreationRequest featureTextCreationRequest) {
        featureService.saveFeatureText(featureTextCreationRequest);
    }

    @GetMapping("/api/text-features/{id}")
    public Optional<FeatureTextBackofficeModel> allText(@PathVariable(name = "id") String id) {
        return featureService.featureTextOfId(id);
    }


    @GetMapping("/api/text-features")
    public List<FeatureTextBackofficeModel> allText() {
        return featureService.allFeatureTexts();
    }

    @PostMapping("/api/numeric-features")
    public void saveFeatureNumeric(@RequestBody FeatureNumericCreationRequest featureNumericCreationRequest) {
        featureService.saveFeatureNumeric(featureNumericCreationRequest);
    }

    @GetMapping("/api/numeric-features")
    public List<FeatureNumericBackofficeModel> allNumeric() {
        return featureService.allFeatureNumerics();
    }

    @GetMapping("/api/base-features")
    public List<FeatureBaseBackofficeModel> allBase() {
        return featureService.allFeatureBases();
    }

}
