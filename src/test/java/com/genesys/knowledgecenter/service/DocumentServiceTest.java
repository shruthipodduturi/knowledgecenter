package com.genesys.knowledgecenter.service;

import java.io.FileInputStream;
import java.io.IOException;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.genesys.knowledgecenter.domain.Document;
import com.genesys.knowledgecenter.domain.FAQDocument;
import com.genesys.knowledgecenter.error.InvalidDocTypeException;
import com.genesys.knowledgecenter.repositories.DocumentRepository;
import com.genesys.knowledgecenter.utility.DocumentFactory;

@RunWith(SpringRunner.class)
public class DocumentServiceTest {
	
	
	
	@InjectMocks
	private DocumentService documentService;
	
	@Mock
	private DocumentRepository documentRepository;
	
	@Mock
	private DocumentFactory documentFactory;
	
	
	@Test
	public void uploadFile_Success() throws Exception {
		
		Document document = new Document();
		document.setDocType("FAQ");
		document.setCategoryId(new ObjectId("5da74e1c19cef7c42475a1e2"));
		document.setLocale("en");
		document.setQuestion("How much is the interest on home loan?");
		document.setAnswer("current interest is 8%");
		
		Mockito.doReturn(new FAQDocument()).when(documentFactory).getDocument(Mockito.any(String.class));
		
		Mockito.doReturn(document).when(documentRepository).
		insert(Mockito.any(Document.class));
		

		FileInputStream inputFile = new FileInputStream("src/test/resources/document.json");
		
		MockMultipartFile multipartFile = new MockMultipartFile("file","document.json",
				"multipart/form-data",inputFile);
		
		documentService.saveDocument(multipartFile,new ObjectId("5da80ad619cef7d82c670692"));

	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void uploadFile_WhenInvalidtext_throwsIllegalArgumentException() throws Exception {
		
		Document document = new Document();
		document.setDocType("FAQ");
		document.setCategoryId(new ObjectId("5da74e1c19cef7c42475a1e2"));
		document.setLocale("en");
		document.setQuestion("How much is the interest on home loan?");
		document.setAnswer("current interest is 8%");
		
		Mockito.doReturn(new Document()).when(documentFactory).getDocument(Mockito.any(String.class));
		
		Mockito.doReturn(document).when(documentRepository).
		insert(Mockito.any(Document.class));
		

		FileInputStream inputFile = new FileInputStream("src/test/resources/invalidText.json");
		
		MockMultipartFile multipartFile = new MockMultipartFile("file","invalidText.json",
				"multipart/form-data",inputFile);
		
		documentService.saveDocument(multipartFile,new ObjectId("5da80ad619cef7d82c670692"));

	}
	

	@Test(expected = InvalidDocTypeException.class)
	public void uploadFile_WhenInvalidDocType_throwsInvalidDocTypeException() throws Exception {
		
		Document document = new Document();
		document.setDocType("FAQ");
		document.setCategoryId(new ObjectId("5da74e1c19cef7c42475a1e2"));
		document.setLocale("en");
		document.setQuestion("How much is the interest on home loan?");
		document.setAnswer("current interest is 8%");
		
		Mockito.doReturn(null).when(documentFactory).getDocument(Mockito.any(String.class));
		
		Mockito.doReturn(document).when(documentRepository).
		insert(Mockito.any(Document.class));
		

		FileInputStream inputFile = new FileInputStream("src/test/resources/document.json");
		
		MockMultipartFile multipartFile = new MockMultipartFile("file","document.json",
				"multipart/form-data",inputFile);
		
		documentService.saveDocument(multipartFile,new ObjectId("5da80ad619cef7d82c670692"));

	}

	
	

	

}
