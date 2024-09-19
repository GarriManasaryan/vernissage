package com.handicraft.vernissage.port.adapters.backoffice.service;

import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductBackofficeModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {
    // тут селекты и сюда же рекорды замапливать (если прямо сложный) или сразу в BackofficeModel
    // контроллер дергает просто его, не нужен уже бекоффис модели и прочее
    // .

    // тут легально использовать все, что в порте адптере и ProductBackofficeModel можно
    public List<ProductBackofficeModel> searchProducts(){
        // и пыздуй
        return List.of();
    }
}
