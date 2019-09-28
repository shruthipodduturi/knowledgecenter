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
import com.genesys.knowledgecenter.repositories.DocumentRepository;

@Service
public class DocumentService {
	
	public static final String DOC_ID = "docId";
	public static final String DOC_TYPE ="docType";
    public static final String DOC_TITLE = "title";
    public static final String DOC_LOCALE = "locale";
    public static final String DOC_CONTENT = "content";
    public static final String DOC_QUESTION = "question";
    public static final String DOC_ANSWER = "answer";
    public static final String DOC_CATEGORY_Id = "categoryId";
    
	
	
	@Autowired
	private DocumentRepository docRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	public void saveDocument(MultipartFile file, ObjectId kbaseId){
		
		try{
			File f = convert(file);
			JsonFactory jsonFactory = new JsonFactory();
		
			JsonParser jsonParser = jsonFactory.createJsonParser(f);
			Document document = new Document();
			
			
			JsonToken jsonToken = jsonParser.nextToken();
            while (jsonToken!= JsonToken.END_ARRAY){ //Iterate all elements of array
            	
            	document.setKbaseId(kbaseId);
                String fieldname = jsonParser.getCurrentName(); //get current name of token
                if (DOC_ID.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken(); //read next token
                    document.setId(new ObjectId(jsonParser.getText()));
                }
                if (DOC_LOCALE.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setLocale(jsonParser.getText());
                }
                if (DOC_TYPE.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setDocType(jsonParser.getText());
                }
                if (DOC_CATEGORY_Id.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken(); //read next token
                    document.setCategoryId(new ObjectId(jsonParser.getText()));
                }
                if (DOC_TITLE.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setTitle(jsonParser.getText());
                }
                if (DOC_CONTENT.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setContent(jsonParser.getText());
                }
                if (DOC_QUESTION.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setQuestion(jsonParser.getText());
                }
                if (DOC_ANSWER.equals(fieldname)) { 
                	jsonToken = jsonParser.nextToken();
                    document.setAnswer(jsonParser.getText());
                }
                if(jsonToken==JsonToken.END_OBJECT){
                    
                	//store in DB
                	docRepository.insert(document);
                	
                	
                    document = new Document();
                    
                }
                
                jsonToken = jsonParser.nextToken();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
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
