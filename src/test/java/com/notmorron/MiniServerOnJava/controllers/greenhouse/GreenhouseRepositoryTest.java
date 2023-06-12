package com.notmorron.MiniServerOnJava.controllers.greenhouse;

import com.notmorron.MiniServerOnJava.controllers.greenhouse.service.GreenhouseService;
import com.notmorron.MiniServerOnJava.dbs.postgres.models.GreenhouseTable;
import com.notmorron.MiniServerOnJava.dbs.postgres.repository.GreenhouseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class GreenhouseRepositoryTest {

    @Mock
    private GreenhouseRepository myRepositoryMock;
    @InjectMocks
    private GreenhouseService greenhouseService;

    @Test
    public void testGetData() throws JsonProcessingException {
        GreenhouseTable testEntity = new GreenhouseTable(0, 1, 111, 1000, 121, false, System.currentTimeMillis());

        ObjectMapper obj = new ObjectMapper();
        var data = obj.writeValueAsString(testEntity);

        greenhouseService.addLog("GreenHouse/1", data);
    }
}