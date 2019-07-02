package com.hippo.coresurvey.web.rest.service;

import com.hippo.coresurvey.domain.analytics.AnalyticsService;
import com.hippo.coresurvey.domain.analytics.SurveyAnalyticsData;
import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.web.rest.resource.SubmissionRestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.yml")
public class AnalyticsRestService implements AnalyticsService {

  private final RestTemplate restTemplate;

  @Value("${service.user-analytics.host}")
  private String analyticsHost;

  @Value("${service.user-analytics.port}")
  private Integer analyticsPort;

  @Autowired
  public AnalyticsRestService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public void trainOnAnalyticsData(SurveyAnalyticsData analyticsData) {

    String url = String.format("http://%s:%s/train", analyticsHost, analyticsPort);
    ResponseEntity response = restTemplate.postForEntity(url, analyticsData, String.class);

    // retry once if error
    if (response.getStatusCode().isError()) {
      response = restTemplate.postForEntity(url, analyticsData, String.class);
    }

    System.out.println(response.getStatusCode());
  }

  @Override
  public List<Submission> predictUserDetailsForSubmissions(List<Submission> submissions) {

    if (submissions.isEmpty()) {
      return submissions;
    }

    AnalyticsSubmissionsResource resource = new AnalyticsSubmissionsResource(submissions);

    String surveyId = submissions.get(0).getSurvey().getId();

    String url = String.format("http://%s:%s/predict/%s", analyticsHost, analyticsPort, surveyId);
    ResponseEntity<AnalyticsSubmissionsResource> response =
        restTemplate.postForEntity(url, resource, AnalyticsSubmissionsResource.class);

//    if (response.getStatusCode().isError() || response.getBody() == null) {
      return submissions;
//    } else {
//      return response.getBody().submissions.stream()
//          .map(SubmissionRestResource::toDomainObject)
//          .collect(Collectors.toList());
//    }
  }
}
