package com.hippo.coresurvey.web.rest.service;

import com.hippo.coresurvey.domain.submission.Submission;

import java.util.ArrayList;
import java.util.List;


public class AnalyticsSubmissionsResource {
  public ArrayList<Submission> submissions;

  public AnalyticsSubmissionsResource() {
  }

  public AnalyticsSubmissionsResource(List<Submission> submissions) {
    this.submissions = new ArrayList<>(submissions);
  }
}
