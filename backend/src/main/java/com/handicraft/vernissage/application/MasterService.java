package com.handicraft.vernissage.application;

import com.handicraft.vernissage.domain.product.master.Master;
import com.handicraft.vernissage.domain.product.master.MasterRepo;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.master.MasterBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.master.MasterCreationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterService {

    private final MasterRepo masterRepo;

    public MasterService(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    public void save(MasterCreationRequest masterCreationRequest) {
        masterRepo.save(Master.of(
                masterCreationRequest.name(),
                masterCreationRequest.description()
        ));
    }

    public List<MasterBackofficeModel> all() {
        return masterRepo.all().stream()
                .map(x -> new MasterBackofficeModel(
                        x.id(),
                        x.name(),
                        x.description()
                ))
                .toList();
    }
}
