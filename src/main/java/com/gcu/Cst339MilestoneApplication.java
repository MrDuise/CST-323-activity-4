package com.gcu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sun.tools.javac.Main;

@ComponentScan({ "com.gcu"})
@SpringBootApplication
public class Cst339MilestoneApplication {

	public static void main(String[] args) {
		 Logger logger = LoggerFactory.getLogger(Main.class);
		SpringApplication.run(Cst339MilestoneApplication.class, args);
		logger.info("MADE BY MICHAEL DUISENBERG");
	}

}
