package com.genesys.knowledgecenter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.genesys.knowledgecenter.domain.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
	

}
