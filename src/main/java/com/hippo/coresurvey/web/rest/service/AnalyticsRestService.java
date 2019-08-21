package com.hippo.coresurvey.web.rest.service;

import com.hippo.coresurvey.domain.analytics.AnalyticsService;
import com.hippo.coresurvey.domain.analytics.SurveyAnalyticsData;
import com.hippo.coresurvey.domain.submission.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:application.yml")
public class AnalyticsRestService implements AnalyticsService {

  @Autowired
  public AnalyticsRestService() {
  }

  @Override
  public void trainOnAnalyticsData(SurveyAnalyticsData analyticsData) {

  }

  @Override
  public List<Submission> predictUserDetailsForSubmissions(List<Submission> submissions) {
    return submissions;
  }
}
