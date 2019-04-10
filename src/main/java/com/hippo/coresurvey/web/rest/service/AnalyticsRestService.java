package com.hippo.coresurvey.web.rest.service;

import com.hippo.coresurvey.domain.analytics.AnalyticsService;
import com.hippo.coresurvey.domain.submission.Submission;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@PropertySource("classpath:application.yml")
public class AnalyticsRestService implements AnalyticsService {

  private final RestTemplate restTemplate;

  private final EurekaClient eurekaClient;

  @Value("${service.user-analytics.serviceId}")
  private String analyticsServiceId;

  @Autowired
  public AnalyticsRestService(RestTemplate restTemplate, EurekaClient eurekaClient) {
    this.restTemplate = restTemplate;
    this.eurekaClient = eurekaClient;
  }

  @Override
  public void sendSubmissionBatch(List<Submission> submissions) {
    Application application = eurekaClient.getApplication(analyticsServiceId);
    InstanceInfo instanceInfo = application.getInstances().get(0);
    String url = String.format("http://%s:%s/", instanceInfo.getIPAddr(), instanceInfo.getPort());

    String result = restTemplate.getForObject(url, String.class);

    System.out.println(result);
  }
}
