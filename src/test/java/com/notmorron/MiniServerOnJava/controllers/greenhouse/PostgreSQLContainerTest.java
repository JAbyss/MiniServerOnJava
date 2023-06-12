package com.notmorron.MiniServerOnJava.controllers.greenhouse;

import com.notmorron.MiniServerOnJava.dbs.postgres.models.GreenhouseTable;
import com.notmorron.MiniServerOnJava.dbs.postgres.repository.GreenhouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
public class PostgreSQLContainerTest {

    @Autowired
    private GreenhouseRepository greenhouseRepository;

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("garden")
            .withUsername("jabyss")
            .withPassword("123456789")
            .withExposedPorts(5432);

    static String test() {
        return "create-drop";
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", PostgreSQLContainerTest::test);
    }

    @Test
    public void testPostgresContainer() {
        postgresContainer.start();
        GreenhouseTable testEntity = new GreenhouseTable(1, 1, 111, 1000, 121, false, System.currentTimeMillis());

        greenhouseRepository.save(testEntity);

        List<GreenhouseTable> all = greenhouseRepository.findByIdGreenhouse(1);
        assertThat(all.get(0).getId()).isEqualTo(testEntity.getId());
        postgresContainer.stop();
    }
}