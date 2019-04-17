package com.hippo.coresurvey.web.rest.service;

import com.hippo.coresurvey.domain.analytics.AnalyticsData;
import com.hippo.coresurvey.domain.analytics.AnalyticsService;
import com.hippo.coresurvey.domain.submission.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@PropertySource("classpath:application.yml")
public class AnalyticsRestService implements AnalyticsService {

  private final RestTemplate restTemplate;

  private final LoadBalancerClient client;

  @Value("${service.user-analytics.serviceId}")
  private String analyticsServiceId;

  @Autowired
  public AnalyticsRestService(RestTemplate restTemplate, LoadBalancerClient client) {
    this.restTemplate = restTemplate;
    this.client = client;
  }

  @Override
  public void sendAnalyticsData(AnalyticsData analyticsData) {
    ServiceInstance instance = client.choose(analyticsServiceId);

    if (instance == null) {
      return;
    }

    String url = String.format("http://%s:%s/", instance.getHost(), instance.getPort());
    String result = restTemplate.getForObject(url, String.class);

    System.out.println(result);
  }
}
