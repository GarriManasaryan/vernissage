package com.handicraft.vernissage.application;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @Test
    public void testЫщьу(){
        Optional<Double> test = Optional.ofNullable(0.0);
        var res = test.orElse(null);
        System.out.println(test);
        System.out.println(res);

    }

}