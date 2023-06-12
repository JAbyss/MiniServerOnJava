package com.notmorron.MiniServerOnJava.controllers.greenhouse.service;

import com.google.gson.Gson;
import com.notmorron.MiniServerOnJava.controllers.greenhouse.models.GreenhouseMessage;
import com.notmorron.MiniServerOnJava.dbs.postgres.models.GreenhouseTable;
import com.notmorron.MiniServerOnJava.dbs.postgres.repository.GreenhouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class GreenhouseService {
    private final GreenhouseRepository greenHouseRepository;

    public List<GreenhouseTable> getAll() {
        Iterable<GreenhouseTable> optionalGreenHouseLogs = greenHouseRepository.findAll();
        List<GreenhouseTable> greenHouseLogsList = new ArrayList<>();
        optionalGreenHouseLogs.forEach(greenHouseLogsList::add);
        return greenHouseLogsList;
    }

    public List<GreenhouseTable> getById(int idGreenHouse){
        Iterable<GreenhouseTable> optionalGreenHouseLogs = greenHouseRepository.findByIdGreenhouse(idGreenHouse);
        List<GreenhouseTable> greenHouseLogsList = new ArrayList<>();
        optionalGreenHouseLogs.forEach(greenHouseLogsList::add);
        return greenHouseLogsList;
    }

    public void addLog(String topic, String message){
        Gson gson = new Gson();
        GreenhouseMessage object = gson.fromJson(message, GreenhouseMessage.class);

        Pattern pattern = Pattern.compile("\\d+"); // Регулярное выражение для числа.
        Matcher matcher = pattern.matcher(topic);
        if (matcher.find()) {
            String number = matcher.group();
            greenHouseRepository.save(object.toTable(Integer.parseInt(number)));
        } else {
            System.out.println("Number not found in the text.");
            throw new IllegalArgumentException();
        }
    }
}