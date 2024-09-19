package com.handicraft.gw.port.adapters.backoffice.resource;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public record TestRec(@NotNull String id){}

    @GetMapping("/api/test")
    public TestRec getTest(){
        return new TestRec("test");
    }

}
