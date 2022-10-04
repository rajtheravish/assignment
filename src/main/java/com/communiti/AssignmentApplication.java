package com.communiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.communiti.crypt.KeyInit;
import com.communiti.crypt.RSA;

@SpringBootApplication
public class AssignmentApplication {

	RSA userA = new RSA();
	RSA userB = new RSA();
	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}
	
	//Initialization of users
	@Bean
	public KeyInit keyInit() {
		KeyInit keyInit = new KeyInit();
		return keyInit;
	}

}
