package br.solutis.livepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LivePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivePayApplication.class, args);
	}

}
