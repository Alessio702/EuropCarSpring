package com.example.demo;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{
	/*
	 * URL Swagger: http://localhost:5051/swagger-ui.html
	 * Docs: https://swagger.io/docs/
	 * http://localhost:8080/v2/api-docs
	 */

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
				.paths(regex("/OrdineAcquisto.*")).build().apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("EUROPCAT WEB SERVICE API").description("Spring Boot REST API per la gestione ordini acquisto")
                .version("1.0.0").license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Alessio Di Giorgio", "https://xantrix.it/info", "a.digiorgio@softwareindustriali.it")).build();
	}
}
