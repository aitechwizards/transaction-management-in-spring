package com.aitechwizards.secure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SecureRestApiApplication {
	private static final Logger LOG = LoggerFactory.getLogger(SecureRestApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SecureRestApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				LOG.info("Cross Called >>>>>>>>>>> ");
				System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF ");
				registry.addMapping("/info/**").allowedOrigins("http://localhost:8080", "http://localhost:8787");
			}
		};
	}

	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// LOG.info("Cross Called ");
	//
	// registry.addMapping("/info/**").allowedOrigins("http://localhost:8585",
	// "http://localhost:8787");
	// }
}
