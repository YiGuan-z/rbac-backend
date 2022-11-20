package com.cqsd;

import com.cqsd.util.ApplicationContent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		final var context = SpringApplication.run(Application.class, args);
		ApplicationContent.setContext(context);
	}
}
