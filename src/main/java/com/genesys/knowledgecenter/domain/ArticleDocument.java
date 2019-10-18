package com.genesys.knowledgecenter.domain;

import org.bson.types.ObjectId;

public class ArticleDocument extends Document{
	
	public ArticleDocument() {
		super();
	}

	public ArticleDocument(String docType, String locale, ObjectId kbaseId, ObjectId categoryId) {
		super(docType, locale, kbaseId, categoryId);
	}

	

}
