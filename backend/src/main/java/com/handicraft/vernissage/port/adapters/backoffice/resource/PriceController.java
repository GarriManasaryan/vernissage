package com.handicraft.vernissage.port.adapters.backoffice.resource;

import com.handicraft.vernissage.application.PriceService;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.price.PriceBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.price.PriceCreationRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/api/prices")
    public void save(@RequestBody @NotNull PriceCreationRequest priceCreationRequest) {
        priceService.save(priceCreationRequest);
    }

    @GetMapping("/api/prices")
    public List<PriceBackofficeModel> all() {
        return priceService.all();
    }

}
