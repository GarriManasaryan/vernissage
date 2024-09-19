package com.handicraft.gateway.test.application;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

//@Testcontainers
@SpringBootTest
@Transactional
abstract public class DatabaseBaseTest {

    static final PostgreSQLContainer<?> postgresqlContainer;

    static {
        postgresqlContainer = new PostgreSQLContainer<>("postgres:14.5")
                .withDatabaseName("db_vernissage")
                .withUsername("postgres")
                .withPassword("1");
        postgresqlContainer.start();
    }

//    @Container
//    private static GenericContainer postgresqlContainer = new PostgreSQLContainer("postgres:14.5")
//            .withDatabaseName("team_management")
//            .withUsername("postgres")
//            .withPassword("1")
//            .withReuse(true);

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> ((PostgreSQLContainer) postgresqlContainer).getJdbcUrl());
        registry.add("spring.datasource.password", () -> ((PostgreSQLContainer) postgresqlContainer).getPassword());
        registry.add("spring.datasource.username", () -> ((PostgreSQLContainer) postgresqlContainer).getUsername());
    }


}
