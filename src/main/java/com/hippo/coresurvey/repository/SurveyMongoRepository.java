package com.hippo.coresurvey.repository;

import com.hippo.coresurvey.domain.Survey;
import com.hippo.coresurvey.domain.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SurveyMongoRepository implements SurveyRepository {

  private final SurveyMongoStore repo;

  @Autowired
  public SurveyMongoRepository(SurveyMongoStore repo) {
    this.repo = repo;
  }

  @Override
  public List<Survey> getAllSurveys() {
    return repo.findAll().stream()
        .map(SurveyMongoEntity::toDomainObject)
        .collect(Collectors.toList());
  }

  @Override
  public List<Survey> getAllSurveysMetadata() {
    return repo.findAllAndExcludeQuestions().stream()
        .map(SurveyMongoEntity::toDomainObject)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Survey> getSurveyById(String id) {
    Optional<SurveyMongoEntity> survey = repo.findById(id);

    return survey.map(SurveyMongoEntity::toDomainObject);
  }

  @Override
  public Survey addSurvey(Survey survey) {
    SurveyMongoEntity entity = SurveyMongoEntity.fromDomainObject(survey);

    return repo.insert(entity).toDomainObject();
  }
}
