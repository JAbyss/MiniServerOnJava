package com.notmorron.MiniServerOnJava.controllers.greenhouse.models;

import com.notmorron.MiniServerOnJava.dbs.postgres.models.GreenhouseTable;

public class GreenhouseMessage {

    int sensor_temperature;
    int sensor_soil_moisture;
    int sensor_humidity_air;
    boolean status_pump;

    public GreenhouseTable toTable(int idGreenhouse) {
        return new GreenhouseTable(0, idGreenhouse, sensor_temperature, sensor_soil_moisture, sensor_humidity_air, status_pump, System.currentTimeMillis());
    }
}