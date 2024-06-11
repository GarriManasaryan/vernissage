package com.handicraft.vernissage.application;

import com.handicraft.vernissage.domain.product.price.Price;
import com.handicraft.vernissage.domain.product.price.PriceRepo;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.price.PriceBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.price.PriceCreationRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.nio.DoubleBuffer;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepo priceRepo;

    public PriceService(PriceRepo priceRepo) {
        this.priceRepo = priceRepo;
    }

    public void save(@NotNull PriceCreationRequest priceCreationRequest) {
        priceRepo.save(Price.of(
                priceCreationRequest.value(),
                priceCreationRequest.currency(),
                priceCreationRequest.discount().orElse(null)
        ));
    }

    public List<PriceBackofficeModel> all() {

        return priceRepo.all().stream()
                .map(price -> new PriceBackofficeModel(
                        price.id(),
                        price.value(),
                        price.currency(),
                        price.discount().orElse(null)
                ))
                .toList();
    }
}
