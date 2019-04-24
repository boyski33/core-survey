package com.hippo.coresurvey.web.rest.service;

import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.web.rest.resource.SubmissionRestResource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AnalyticsSubmissionsResource {
  public ArrayList<SubmissionRestResource> submissions;

  public AnalyticsSubmissionsResource() {
  }

  public AnalyticsSubmissionsResource(List<Submission> submissions) {
    this.submissions = submissions.stream()
        .map(SubmissionRestResource::fromDomainObject)
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
