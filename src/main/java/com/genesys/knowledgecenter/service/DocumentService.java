package com.genesys.knowledgecenter.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genesys.knowledgecenter.domain.Document;
import com.genesys.knowledgecenter.error.InvalidDocTypeException;
import com.genesys.knowledgecenter.repositories.DocumentRepository;
import com.genesys.knowledgecenter.utility.DocumentFactory;

@Service
public class DocumentService {
	
	public static final String DOC_ID = "docId";
	public static final String DOC_TYPE ="docType";
    public static final String DOC_TITLE = "title";
    public static final String DOC_LOCALE = "locale";
    public static final String DOC_CONTENT = "content";
    public static final String DOC_QUESTION = "question";
    public static final String DOC_ANSWER = "answer";
    public static final String DOC_CATEGORY_ID = "categoryId";
    
	
	
	@Autowired
	private DocumentRepository docRepository;
	
	@Autowired
	private DocumentFactory documentFactory;
	
	
	public void saveDocument(MultipartFile file, ObjectId kbaseId) throws IOException{
		
		try{
			
			
			File f = convert(file);
			
			JsonFactory jsonFactory = new JsonFactory();
		
			JsonParser jsonParser = jsonFactory.createJsonParser(f);
			
			Document document = null;
			JsonToken jsonToken = jsonParser.nextToken();
			
            while (jsonToken!= JsonToken.END_ARRAY){ //Iterate all elements of array
            	
                String fieldname = jsonParser.getCurrentName(); //get current name of token
                
                if(fieldname != null) {
	                switch(fieldname) {
	                
		                case DOC_TYPE:
		                	jsonParser.nextToken();
		                	document = documentFactory.getDocument(jsonParser.getText());
		                	if(document == null)
		                		throw new InvalidDocTypeException("Invalid Document Type");
		                    document.setDocType(jsonParser.getText());
		                    break;
		                case DOC_ID:
		                	jsonParser.nextToken();
		                    document.setId(new ObjectId(jsonParser.getText()));
		                    break;
		                case DOC_LOCALE:
		                	jsonParser.nextToken();
		                    document.setLocale(jsonParser.getText());
		                    break;
		                case DOC_CATEGORY_ID:
		                	jsonParser.nextToken();
		                    document.setCategoryId(new ObjectId(jsonParser.getText()));
		                    break;
		                case DOC_TITLE:
		                	jsonParser.nextToken();
		                    document.setTitle(jsonParser.getText());
		                    break;
		                case DOC_CONTENT:
		                	jsonParser.nextToken();
		                    document.setContent(jsonParser.getText());
		                    break;
		                case DOC_QUESTION:
		                	jsonParser.nextToken();
		                    document.setQuestion(jsonParser.getText());
		                    break;
		                case DOC_ANSWER:
		                	jsonParser.nextToken();
		                    document.setAnswer(jsonParser.getText());
		                    break;
		                default:
		                	break;
		                	
	                }
                }
                
                if(jsonToken==JsonToken.END_OBJECT){
                	//store in DB
                	document.setKbaseId(kbaseId);
                	docRepository.insert(document);
                    
                }
                jsonToken = jsonParser.nextToken();
            }
            
        } catch(IllegalArgumentException e) {
        	throw new IllegalArgumentException("Incorrect Text Format");
        } catch (IOException e) {
        	throw new IOException("Error in Parsing JSON"); 
        } 
		
		
	}
	
	
	public List<Document> findDocumentsByKnowledgeBaseId(ObjectId kbaseId){
		return docRepository.findDocumentsByKnowledgeBaseId(kbaseId);
		
	}
	
	public List<Document> findDocumentsByKnowledgeBaseIdAndCategory(ObjectId kbaseId,ObjectId categoryId){
		return docRepository.findDocumentsByKnowledgeBaseIdAndCategoryId(kbaseId,categoryId);
	}
	
	public void updateDocument(Document document,ObjectId kbaseId){
		 document.setKbaseId(kbaseId);
		 docRepository.save(document);
	}
	
	public void deleteDocument(ObjectId docId){
		docRepository.deleteById(docId.toHexString());
	}
	
	
	public List<Document> searchDocumentsByKeyword(String keyword){
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(keyword);
		return docRepository.findAllBy(criteria);
	}
	
	public  File convert(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile();
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}

	

}
