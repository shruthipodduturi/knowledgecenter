package com.genesys.knowledgecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.genesys.knowledgecenter"})
public class KnowledgecenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgecenterApplication.class, args);
	}

}
