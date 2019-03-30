package com.hippo.coresurvey.domain.survey;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository {

  List<Survey> getAllSurveys();

  List<Survey> getAllSurveysMetadata();

  Optional<Survey> getSurveyById(String id);

  List<Survey> getSurveysForOwner(String ownerEmail);

  Survey addSurvey(Survey survey);
}
