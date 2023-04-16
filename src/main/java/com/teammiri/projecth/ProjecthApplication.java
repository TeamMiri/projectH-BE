package com.teammiri.projecth;

import com.teammiri.projecth.config.properties.AppProperties;
import com.teammiri.projecth.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CorsProperties.class, AppProperties.class})
public class ProjecthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjecthApplication.class, args);
	}

}
