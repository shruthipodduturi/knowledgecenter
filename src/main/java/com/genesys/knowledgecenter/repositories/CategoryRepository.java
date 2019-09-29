package com.genesys.knowledgecenter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.genesys.knowledgecenter.domain.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
	
	
	

}
