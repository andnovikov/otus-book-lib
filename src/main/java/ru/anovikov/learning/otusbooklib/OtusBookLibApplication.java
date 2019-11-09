package ru.anovikov.learning.otusbooklib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OtusBookLibApplication {

	public static void main(String[] args) throws Exception{
		ApplicationContext context = SpringApplication.run(OtusBookLibApplication.class, args);
	}

}