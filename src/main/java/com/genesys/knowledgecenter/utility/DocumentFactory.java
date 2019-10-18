package com.genesys.knowledgecenter.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genesys.knowledgecenter.domain.ArticleDocument;
import com.genesys.knowledgecenter.domain.Document;
import com.genesys.knowledgecenter.domain.FAQDocument;

@Component
public class DocumentFactory {
	
	final static Map<String,Document> map = new HashMap<>();
	static {
		map.put("FAQ",new FAQDocument());
		map.put("ARTICLE",new ArticleDocument());
		
	}
	
	public Document getDocument(String documentType) {
		
		return map.get(documentType.toUpperCase());
		
		
	}

}
