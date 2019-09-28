package com.genesys.knowledgecenter.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesys.knowledgecenter.domain.KnowledgeBase;
import com.genesys.knowledgecenter.repositories.CategoryRepository;
import com.genesys.knowledgecenter.repositories.KnowledgeBaseRepository;

@Service
public class KnowledgeBaseService {
	
	@Autowired
	private KnowledgeBaseRepository knowledgeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public KnowledgeBase createKnowledgeBase(KnowledgeBase kbase){
		categoryRepository.insert(kbase.getCategories());
		KnowledgeBase knowbase = knowledgeRepository.insert(kbase);
		return knowbase;
	}
	
	public List<KnowledgeBase> getKnowledgeBases(){
		return knowledgeRepository.findAll();
	}
	
	public Optional<KnowledgeBase> getKnowledgeBaseById(ObjectId kbaseId){
		return knowledgeRepository.findById(kbaseId.toHexString());
	}

}
