package com.hippo.coresurvey.repository.submission;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubmissionMongoStore extends MongoRepository<SubmissionMongoEntity, String> {
}
