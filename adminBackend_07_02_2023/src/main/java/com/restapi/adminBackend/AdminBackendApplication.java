package com.restapi.adminBackend;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AdminBackendApplication{
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(AdminBackendApplication.class, args);
	}
// implements CommandLineRunner
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println(this.passwordEncoder.encode("test2@123"));
//		
//	}
//
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
