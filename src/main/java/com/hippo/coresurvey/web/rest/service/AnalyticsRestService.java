package com.hippo.coresurvey.web.rest.service;

import com.hippo.coresurvey.domain.analytics.AnalyticsService;
import com.hippo.coresurvey.domain.analytics.SurveyAnalyticsData;
import com.hippo.coresurvey.domain.submission.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
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
  public void sendAnalyticsData(SurveyAnalyticsData analyticsData) {
    ServiceInstance instance = client.choose(analyticsServiceId);

    if (instance == null) {
      return;
    }

    String url = String.format("http://%s:%s/train", instance.getHost(), instance.getPort());
    ResponseEntity result = restTemplate.postForEntity(url, analyticsData, String.class);

    // retry once if error
    if (result.getStatusCode().isError()) {
      result = restTemplate.postForEntity(url, analyticsData, String.class);
    }

    System.out.println(result.getStatusCode());
  }

  @Override
  public List<Submission> predictUserDetailsForSubmissions(List<Submission> submissions) {
    ServiceInstance instance = client.choose(analyticsServiceId);

    if (instance == null) {
      return Collections.emptyList();
    }



    return null;
  }
}
