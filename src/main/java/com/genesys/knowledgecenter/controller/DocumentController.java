package com.genesys.knowledgecenter.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genesys.knowledgecenter.domain.Document;
import com.genesys.knowledgecenter.domain.DocumentWrapper;
import com.genesys.knowledgecenter.service.DocumentService;

@RestController
@RequestMapping("/document")
public class DocumentController {
	
	@Autowired
	private DocumentService docService;
	
	
	
	
	@PostMapping({"/{kbaseId}/upload"})
	public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("kbaseId") ObjectId kbaseId,
			RedirectAttributes redirectAttributes){
		
		docService.saveDocument(file, kbaseId);
		return new ResponseEntity(HttpStatus.OK);
		
		
	}
	
	@GetMapping({"/findAll/{kbaseId}"})
	public DocumentWrapper findDocumentsByKnowledgeBaseId(@PathVariable("kbaseId") ObjectId kbaseId){
		
		List<Document> documents = docService.findDocumentsByKnowledgeBaseId(kbaseId);
		DocumentWrapper docWrapper = new DocumentWrapper();
		docWrapper.setDocuments(documents);
		return docWrapper;
	}
	
	
	@GetMapping({"/findBy/{kbaseId}/{categoryId}"})
	public DocumentWrapper findDocumentsByKnowledgeBaseId(@PathVariable("kbaseId") ObjectId kbaseId,
			@PathVariable("categoryId") ObjectId categoryId){
		
		List<Document> documents = docService.findDocumentsByKnowledgeBaseIdAndCategory(kbaseId,categoryId);
		DocumentWrapper docWrapper = new DocumentWrapper();
		docWrapper.setDocuments(documents);
		return docWrapper;
	}
	
	@PutMapping("/{kbaseId}")
	public ResponseEntity updateDocument(@RequestBody Document document,
			@PathVariable("kbaseId") ObjectId kbaseId){
		docService.updateDocument( document,kbaseId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{docId}")
	public ResponseEntity deletDocument(@PathVariable("docId")ObjectId docId){
		docService.deleteDocument(docId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("search/{keyword}")
	public DocumentWrapper searchDocumentsBykeyword(@PathVariable("keyword") String keyword){
		List<Document> docs = docService.searchDocumentsByKeyword(keyword);
		DocumentWrapper docWrapper = new DocumentWrapper();
		docWrapper.setDocuments(docs);
		return docWrapper;
	}
}
