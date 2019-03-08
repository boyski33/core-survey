package com.hippo.coresurvey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CoreSurveyApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoreSurveyApplication.class, args);
  }

}

@RestController
class SurveyRestController {
  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("service-instances/{applicationName}")
  public List<ServiceInstance> serviceInstancesByApplicationName(
      @PathVariable String appName) {
    return this.discoveryClient.getInstances(appName);
  }
}