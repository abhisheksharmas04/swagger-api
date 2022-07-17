package com.example.swaggerapi.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocConfig {

	@Bean
	public Docket createDocket() {
		return new Docket(DocumentationType.SWAGGER_2) // UI Screen Type
				.select() 
				.apis(RequestHandlerSelectors.basePackage("com.example.swaggerapi.controller")) // base package for rest controllers
				.paths(PathSelectors.regex("/tourist.*")) // to specify request paths
				.build() // build the docket object
				.useDefaultResponseMessages(true)
				.apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
		Contact contact = new Contact("raja","http://www.hcl.com/touricst","abhisheksharmaas04@gmail.com");
		return new ApiInfo("Tourist API","Gives Info about tourist activities",
				"3.4.REALEASE","http://hcl.com/licens", contact,"GNU-Public","http://apache.org/license/gnu",Collections.EMPTY_LIST);
	}
}
