package com.example.demo;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootrestdemoApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringbootrestdemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootrestdemoApplication.class, args);
		logger.info("Message logged at info level");
		logger.error("Message logged at error level");
		logger.debug("Message logged at debug level");
		logger.warn("Message logged at warn level");
		logger.trace("Message logged at trace level");
	}

	@Bean
	public Docket mySwaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.example"))
				.build().apiInfo(customApiInfo());
	}

	// for customizing the api info of our documentation
	public ApiInfo customApiInfo() {
		return new ApiInfo("Nurse API", "Simple APIs for crud operation on Nurse", "1.0", "Free to use",
				new Contact("Akash Gupta", "http://capgemini.com", "akash.a.gupta@capgemini.com"), "API License",
				"http://capgemini.com", Collections.emptyList());
	}

}
