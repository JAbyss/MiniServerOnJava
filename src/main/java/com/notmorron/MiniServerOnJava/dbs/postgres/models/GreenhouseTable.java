package com.notmorron.MiniServerOnJava.dbs.postgres.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "green_houses")
public class GreenhouseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int idGreenhouse;
    int temperature;
    int soil_moisture;
    int humidity_air;
    boolean status_pump;
    long data;
}