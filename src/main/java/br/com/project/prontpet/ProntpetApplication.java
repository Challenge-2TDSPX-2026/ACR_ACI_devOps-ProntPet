package br.com.project.prontpet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {org.springdoc.core.configuration.SpringDocDataRestConfiguration.class,
        org.springdoc.core.configuration.SpringDocHateoasConfiguration.class})
@EnableCaching
public class ProntpetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProntpetApplication.class, args);
	}

}
