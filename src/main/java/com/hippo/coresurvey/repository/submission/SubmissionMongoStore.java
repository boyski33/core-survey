package com.hippo.coresurvey.repository.submission;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubmissionMongoStore extends MongoRepository<SubmissionMongoEntity, String> {

  @Override
  List<SubmissionMongoEntity> findAll();

  @Override
  Optional<SubmissionMongoEntity> findById(String id);

  @Override
  <S extends SubmissionMongoEntity> S insert(S entity);

  List<SubmissionMongoEntity> findBySurveyId(String surveyId);

  @Query(value = "{'user': {$ne: null}, 'survey.id': ?0}", count = true)
  Long countAllBySurveyIdAndUserExists(String surveyId);

  List<SubmissionMongoEntity> findByUserEmail(String userEmail);

  List<SubmissionMongoEntity> findBySurveyIdAndUserEmail(String surveyId, String userEmail);
}
