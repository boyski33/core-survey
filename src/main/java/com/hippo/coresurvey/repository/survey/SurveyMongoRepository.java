package com.hippo.coresurvey.repository.survey;

import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.survey.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SurveyMongoRepository implements SurveyRepository {

  private final SurveyMongoStore surveyStore;

  @Autowired
  public SurveyMongoRepository(SurveyMongoStore surveyStore) {
    this.surveyStore = surveyStore;
  }

  @Override
  public List<Survey> getAllSurveys() {
    return surveyStore.findAll().stream()
        .map(SurveyMongoEntity::toDomainObject)
        .collect(Collectors.toList());
  }

  @Override
  public List<Survey> getAllSurveysMetadata() {
    return surveyStore.findAllAndExcludeQuestions().stream()
        .map(SurveyMongoEntity::toDomainObject)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Survey> getSurveyById(String id) {
    return surveyStore.findById(id)
        .map(SurveyMongoEntity::toDomainObject);
  }

  @Override
  public List<Survey> getSurveysForOwner(String ownerEmail) {
    return surveyStore.findByOwnerEmail(ownerEmail).stream()
        .map(SurveyMongoEntity::toDomainObject)
        .collect(Collectors.toList());
  }

  @Override
  public Survey addSurvey(Survey survey) {
    SurveyMongoEntity entity = SurveyMongoEntity.fromDomainObject(survey);

    return surveyStore.insert(entity).toDomainObject();
  }
}
