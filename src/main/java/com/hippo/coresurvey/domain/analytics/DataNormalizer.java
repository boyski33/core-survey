package com.hippo.coresurvey.domain.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataNormalizer {

  private final AnalyticsService analyticsService;

  @Autowired
  public DataNormalizer(AnalyticsService analyticsService) {
    this.analyticsService = analyticsService;
  }


}
