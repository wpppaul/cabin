package com.babi.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = { "com.babi.common.swagger", "com.babi.*.controller", "com.babi.*.facadeImpl", "com.babi.*.serviceImpl"})
@EnableJpaRepositories(basePackages = { "com.babi.*.repository","com.babi.*.repository.*"})
@EntityScan("com.babi.domain.*")
public class Start {
	@Autowired
	private RestTemplateBuilder builder;
	
	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}
	
	public RestTemplate restTemplate() {
		return builder.build();
	}
}
