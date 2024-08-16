package com.sterlite.el;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LeaveServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveServiceApplication.class, args);
	}

}
