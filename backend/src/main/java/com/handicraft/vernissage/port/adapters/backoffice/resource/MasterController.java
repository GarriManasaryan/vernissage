package com.handicraft.vernissage.port.adapters.backoffice.resource;

import com.handicraft.vernissage.application.MasterService;
import com.handicraft.vernissage.application.ProductCategoryService;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.ProductCategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.ProductCategoryCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.master.MasterBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.master.MasterCreationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class MasterController {

    private final MasterService masterService;

    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @PostMapping("/api/masters")
    public void save(@RequestBody MasterCreationRequest masterCreationRequest) {
        masterService.save(masterCreationRequest);
    }

    @GetMapping("/api/masters")
    public List<MasterBackofficeModel> all() {
        return masterService.all();
    }
}
