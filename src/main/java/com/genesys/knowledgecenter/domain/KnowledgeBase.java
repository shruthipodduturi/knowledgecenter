package com.genesys.knowledgecenter.domain;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class KnowledgeBase {
	
	@Id
	private ObjectId id;
	
	private String knowDescription;
	private String customer;
	private List<String> supportedLanguage;
	
	@DBRef
	private List<Category> categories;
	
	public  KnowledgeBase() {
		// TODO Auto-generated constructor stub
	}
	
	
    @PersistenceConstructor
    public KnowledgeBase(ObjectId id, String knowDescription,String customer,List<String> supportedLanguage){
            super();
            this.id = id;
            this.knowDescription = knowDescription;
            this.customer= customer;
            this.supportedLanguage=supportedLanguage;
    }


	public String getId() {
		return id.toHexString();
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	
	
	public String getKnowDescription() {
		return knowDescription;
	}


	public void setKnowDescription(String knowDescription) {
		this.knowDescription = knowDescription;
	}


	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public List<String> getSupportedLanguage() {
		return supportedLanguage;
	}
	public void setSupportedLanguage(List<String> supportedLanguage) {
		this.supportedLanguage = supportedLanguage;
	}


	public List<Category> getCategories() {
		return categories;
	}


	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	

	
	
	
	
	
	
	
	

}
