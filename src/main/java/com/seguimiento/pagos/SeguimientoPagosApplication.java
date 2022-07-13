package com.seguimiento.pagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.seguimiento.pagos.security.AppProperties;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaAuditing
public class SeguimientoPagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeguimientoPagosApplication.class, args);
	}
	
	// Metodo para encriptar codigo, se usa la etiqueta @Bean para usarse en cualquier Clase
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
	// permita usar la clase del mismo nombre "AppProperties" en cualquier otra clase
	@Bean(name="AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}
	
}
