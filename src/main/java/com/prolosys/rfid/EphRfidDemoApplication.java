package com.prolosys.rfid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class EphRfidDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EphRfidDemoApplication.class, args);
	}

}
