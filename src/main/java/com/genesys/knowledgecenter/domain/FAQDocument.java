package com.genesys.knowledgecenter.domain;

import org.bson.types.ObjectId;

public class FAQDocument extends Document {
	
	public FAQDocument() {
		super();
	}
	
	

	public FAQDocument(String docType, String locale, ObjectId kbaseId, ObjectId categoryId) {
		super(docType, locale, kbaseId, categoryId);
		// TODO Auto-generated constructor stub
	}


}
