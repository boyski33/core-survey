package com.hippo.coresurvey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CoreSurveyApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoreSurveyApplication.class, args);
  }


}