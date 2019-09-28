package com.genesys.knowledgecenter.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.genesys.knowledgecenter.domain.Document;

@Repository
public interface DocumentRepository extends MongoRepository<Document, String> {
	

	
	@Query(value="{'kbaseId':?0}")
	 List<Document>findDocumentsByKnowledgeBaseId(ObjectId kbaseId);
	
	@Query(value="{'kbaseId':?0, 'categoryId':?1}")
	List<Document> findDocumentsByKnowledgeBaseIdAndCategoryId(ObjectId kbaseId, ObjectId categoryId);
	
	
	public List<Document> findAllBy(TextCriteria criteria);
		
		
	

}
