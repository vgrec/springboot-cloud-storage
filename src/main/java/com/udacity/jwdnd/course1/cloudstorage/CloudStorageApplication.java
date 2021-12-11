package com.udacity.jwdnd.course1.cloudstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudStorageApplication {

	/**
	 * brew tap spring-io/tap
	 * brew install spring-boot
	 *
	 * ./mvnw spring-boot:run
	 */
	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}

}
