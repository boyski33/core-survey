package com.hippo.coresurvey.domain.survey;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

import com.hippo.coresurvey.domain.question.Question;
import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.survey.SurveyRepository;

class MockSurveyRepository implements SurveyRepository {

  @Override
  public List<Survey> getAllSurveys() {
    return new ArrayList<>(asList(
        new Survey("id", "title", "description", Instant.now(), asList(new Question())),
        new Survey("id2", "title2", "description2", Instant.now(), asList(new Question()))
    ));
  }

  @Override
  public List<Survey> getAllSurveysMetadata() {
    return new ArrayList<>(asList(
        new Survey("id", "title", "description", Instant.now(), Collections.emptyList()),
        new Survey("id2", "title2", "description2", Instant.now(), Collections.emptyList())
    ));
  }

  @Override
  public Optional<Survey> getSurveyById(String id) {
    return Optional.empty();
  }

  @Override
  public Survey addSurvey(Survey survey) {
    return null;
  }
}

