package com.sqs.jokeapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// main class
@SpringBootApplication
public class JokeApplication {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir") + "\\Test");
		SpringApplication.run(JokeApplication.class, args);
	}
}
