package com.handicraft.vernissage.test.application;

import com.handicraft.vernissage.domain.product.category.CategoryRepo;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseIntegrationTest extends DatabaseBaseTest {

    @Autowired
    CategoryRepo categoryRepo;

    @Test
    void test() {
        // одним тестом покрыл два слоя (два save из разных слоев)
        categoryRepo.all().forEach(x -> System.out.println(x.name()));

    }

//    @Test
//    void test2() {
//        userRepository.save(User.of("dsada", "asasc", Role.MANAGER));
//        userRepository.all().forEach(System.out::println);
//    }

}
