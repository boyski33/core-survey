package com.hippo.coresurvey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CoreSurveyApplication {

  public static final Logger logger = LoggerFactory.getLogger(CoreSurveyApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CoreSurveyApplication.class, args);
  }

}