package com.notmorron.MiniServerOnJava;

import com.notmorron.MiniServerOnJava.controllers.greenhouse.service.GreenhouseService;
import com.notmorron.mqttmodule.MqttClientN;
import com.notmorron.mqttmodule.MqttListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MiniServerOnJavaApplication {

	@Autowired
	private GreenhouseService greenHouseService;

	public static void main(String[] args) {
		SpringApplication.run(MiniServerOnJavaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			var client = new MqttClientN.Builder()
					.setIp("192.168.0.53")
					.setPort("1883")
					.setClientId("GreenHousesClient")
					.build();
			client.subscribeAndListen(
					new MqttListener(greenHouseService::addLog),
					"GreenHouse/#"
			);
		};
	}
}
