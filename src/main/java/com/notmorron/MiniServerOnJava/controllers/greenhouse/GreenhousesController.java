package com.notmorron.MiniServerOnJava.controllers.greenhouse;

import com.notmorron.MiniServerOnJava.controllers.greenhouse.service.GreenhouseService;
import com.notmorron.MiniServerOnJava.dbs.postgres.models.GreenhouseTable;
import com.notmorron.mqttmodule.MqttClientN;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/greenHouses")
public class GreenhousesController {
    @Autowired
    private GreenhouseService greenHouseService;
    @GetMapping("/allGreenHouseLogs")
    public ResponseEntity<List<GreenhouseTable>> getAllGreenHousesLogs() {

        var logs = greenHouseService.getAll();

        return ResponseEntity.ok(logs);
    }

    @GetMapping("/greenHouseLogs/{id}")
    public ResponseEntity<List<GreenhouseTable>> getGreenHouseLogById(@PathVariable("id") int id){

        var logs = greenHouseService.getById(id);

        return ResponseEntity.ok(logs);
    }

    @PostMapping("/commandToPump/{topic}")
    public ResponseEntity.BodyBuilder sendCommandToPump(@PathVariable("topic") String topic, @RequestBody Boolean status) throws MqttException {

        MqttClientN.sendMessage(topic, status.toString());

        return ResponseEntity.ok();
    }
}
