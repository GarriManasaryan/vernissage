package com.handicraft.vernissage.application.common;

import com.handicraft.vernissage.domain.NamedEntity;
import com.handicraft.vernissage.domain.product.ValueExistsRepo;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class EntitySaverService {

    private final RepoValueChecker repoValueChecker;

    public EntitySaverService(RepoValueChecker repoValueChecker) {
        this.repoValueChecker = repoValueChecker;
    }

    public <T extends NamedEntity, R extends ValueExistsRepo> void save(T entity, R repo, Consumer<T> saveFunc){
        RepoValueChecker.checkValueInTable(entity.name(), repo);
        saveFunc.accept(entity);

    }

}
