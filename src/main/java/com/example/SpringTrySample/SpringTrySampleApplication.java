package com.example.SpringTrySample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan // @ConfigurationPropertiesが付与されたクラスをスキャンしてDIコンテナに登録
public class SpringTrySampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTrySampleApplication.class, args);
	}

}
