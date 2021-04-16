package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.NurseAddress;
import com.example.demo.dto.NurseDTO;
import com.example.demo.service.NurseService;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootrestdemoApplication implements CommandLineRunner {
	private static Logger logger = LoggerFactory.getLogger(SpringbootrestdemoApplication.class);

	@Autowired
	private NurseService nurseService;

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

	@Override
	public void run(String... args) throws Exception {
		// It is used to create a nurse's data in the database
		NurseAddress nurseAddress = new NurseAddress();
		nurseAddress.setAddress("Gudari Bazar");
		nurseAddress.setCityName("Rasra");
		nurseAddress.setStateName("U.P.");

		NurseDTO nurseDTO = new NurseDTO();
		nurseDTO.setAge(31);
		nurseDTO.setFirstName("Rama");
		nurseDTO.setLastName("Gupta");
		nurseDTO.setSalary(71000.0);
		nurseDTO.setEmail("rama@gmail.com");
		nurseDTO.setNurseAddress(nurseAddress);
		List<String> depList = new ArrayList<>();
		depList.add("OPD Nursing");
		depList.add("ENT Nursing");
		nurseDTO.setDepList(depList);
		nurseService.addNurse(nurseDTO);
		System.out.println("The nurse's data has been added to db");
	}

}
