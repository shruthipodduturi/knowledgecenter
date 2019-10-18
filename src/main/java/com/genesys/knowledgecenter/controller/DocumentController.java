package com.genesys.knowledgecenter.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genesys.knowledgecenter.domain.Document;
import com.genesys.knowledgecenter.domain.DocumentWrapper;
import com.genesys.knowledgecenter.service.DocumentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/document")
public class DocumentController {
	
	@Autowired
	private DocumentService docService;
	
	
	
	@ApiOperation(value="Upload Documents for given knowledgeBaseId")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully uploaded the file",response=void.class),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 401, message = "Not authorized to upload the document."),
	        @ApiResponse(code = 404, message = "File Not Found")
	    })

	@PostMapping(value={"/{kbaseId}/upload"},produces= {"application/json" })
	public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("kbaseId") ObjectId kbaseId,
			RedirectAttributes redirectAttributes) throws IOException{
		
		if(file.isEmpty()) {
			throw new FileNotFoundException("File Not Found");
		}
		
		docService.saveDocument(file, kbaseId);
		return new ResponseEntity(HttpStatus.OK);
		
		
	}
	
	
	@ApiOperation(value="Retrieve all documents for a given KnowledgeBaseId")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved the documents")
	    })
	@GetMapping(value={"/findAll/{kbaseId}"},produces= {"application/json"})
	public DocumentWrapper findDocumentsByKnowledgeBaseId(@PathVariable("kbaseId") ObjectId kbaseId){
		
		List<Document> documents = docService.findDocumentsByKnowledgeBaseId(kbaseId);
		DocumentWrapper docWrapper = new DocumentWrapper();
		docWrapper.setDocuments(documents);
		return docWrapper;
	}
	
	
	@ApiOperation(value="Retrieve documents for given KnowledgeBaseId and categoryId")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved the documents")
	 })
	@GetMapping(value={"/findBy/{kbaseId}/{categoryId}"},produces= {"application/json" })
	public DocumentWrapper findDocumentsByKnowledgeBaseId(@PathVariable("kbaseId") ObjectId kbaseId,
			@PathVariable("categoryId") ObjectId categoryId){
		
		List<Document> documents = docService.findDocumentsByKnowledgeBaseIdAndCategory(kbaseId,categoryId);
		DocumentWrapper docWrapper = new DocumentWrapper();
		docWrapper.setDocuments(documents);
		return docWrapper;
	}
	
	
	@ApiOperation(value="Update a Document")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Successfully updated the document",response=void.class)
	    })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(value="/{kbaseId}")
	public ResponseEntity updateDocument(@RequestBody Document document,
			@PathVariable("kbaseId") ObjectId kbaseId){
		docService.updateDocument( document,kbaseId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Delete Document by Id")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Successfully deleted the document",response=void.class)
	    })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value="{docId}")
	public ResponseEntity deletDocument(@PathVariable("docId")ObjectId docId){
		docService.deleteDocument(docId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	@ApiOperation(value="Search Documents by keyword")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved the documents by keyword")
	    })
	@GetMapping(value="search/{keyword}",produces= {"application/json" })
	public DocumentWrapper searchDocumentsBykeyword(@PathVariable("keyword") String keyword){
		List<Document> docs = docService.searchDocumentsByKeyword(keyword);
		DocumentWrapper docWrapper = new DocumentWrapper();
		docWrapper.setDocuments(docs);
		return docWrapper;
	}
}
