package com.prolosys.rfid.common.swagger;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "API Restfull SCL", 
	      "Documentación API Restfull SCL SERMA.", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Andres Casique", "http://prologisys.com", "acasique@prologisys.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}

    @Bean
    public Docket api() {
    	return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(
                    RequestHandlerSelectors
                    .basePackage("com.prologisys.scl.microservices"))
            .paths(PathSelectors.any())
            .build().apiInfo(apiInfo());
     }

}
