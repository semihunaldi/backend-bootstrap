package com.semihunaldi.backendbootstrap.services.mongo;

import com.semihunaldi.backendbootstrap.entitymodel.mongo.TestDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by semihunaldi on 9.11.2018
 */
public interface TestRepository extends MongoRepository<TestDocument, String> {

	TestDocument findByUserId(String userId);
	TestDocument findByTestString(String userId);
}
