package com.genesys.knowledgecenter.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;


@org.springframework.data.mongodb.core.mapping.Document
public  class Document {
	
	@Id
	private ObjectId  id;
	
	private String docType;
	
	private String locale;
	
	private @TextIndexed String question;
	
	private @TextIndexed String answer;
	
	private @TextIndexed String title;
	
	private @TextIndexed String content;
	private ObjectId kbaseId;
	
	private ObjectId categoryId;
	
	public Document() {
		
	}
	
	public Document(String docType,String locale,ObjectId kbaseId,ObjectId categoryId) {
		this.docType = docType;
		this.locale = locale;
		this.kbaseId = kbaseId;
		this.categoryId = categoryId;
	}
	
	
	
	public String getId() {
		return id.toHexString();
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKbaseId() {
		return kbaseId.toHexString();
	}
	public void setKbaseId(ObjectId kbaseId) {
		this.kbaseId = kbaseId;
	}
	public String getCategoryId() {
		return categoryId.toHexString();
	}
	public void setCategoryId(ObjectId categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
	
	
	
	
	
	

}
