package com.genesys.knowledgecenter.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.genesys.knowledgecenter.KnowledgecenterApplication;
import com.genesys.knowledgecenter.domain.Document;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=KnowledgecenterApplication.class)
public class DocumentRepositoryTest {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Test
	public void insertDocument() {
		Document document = new Document();
		document.setDocType("FAQ");
		document.setCategoryId(new ObjectId("5da74e1c19cef7c42475a1e2"));
		document.setLocale("en");
		document.setQuestion("How much is the interest on home loan?");
		document.setAnswer("current interest is 8%");
		
		Document docSaved = documentRepository.insert(document);
		assertNotNull(docSaved.getId());
		
		assertEquals(docSaved.getQuestion(),document.getQuestion());
		
		Optional<Document> optional = documentRepository.findById(docSaved.getId());
		assertTrue(optional.isPresent());
		
		Document docRetrieved = optional.get();
		
		assertEquals(docRetrieved.getAnswer(),document.getAnswer());
		
		
		
		
	}

}
