package com.hippo.coresurvey.web.rest.resource;

import com.hippo.coresurvey.domain.stats.SurveyReport;
import com.hippo.coresurvey.domain.submission.Submission;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SurveyReportRestResource {

  public List<SubmissionRestResource> submissions;

  public Map<String, Integer> answerStats;

  public SurveyReportRestResource() {
  }

  public SurveyReportRestResource(List<SubmissionRestResource> submissions, Map<String, Integer> answerStats) {
    this.submissions = submissions;
    this.answerStats = answerStats;
  }

  public SurveyReport toDomainObject() {
    final List<Submission> submissionList = submissions.stream()
        .map(SubmissionRestResource::toDomainObject)
        .collect(Collectors.toList());

    return new SurveyReport(submissionList, answerStats);
  }

  public static SurveyReportRestResource fromDomainObject(SurveyReport report) {
    final List<SubmissionRestResource> submissionList = report.getSubmissions().stream()
        .map(SubmissionRestResource::fromDomainObject)
        .collect(Collectors.toList());

    return new SurveyReportRestResource(
        submissionList,
        report.getAnswerStats()
    );
  }
}
