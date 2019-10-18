package com.genesys.knowledgecenter.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.genesys.knowledgecenter.domain.KnowledgeBase;
import com.genesys.knowledgecenter.domain.KnowledgeBaseWrapper;
import com.genesys.knowledgecenter.service.KnowledgeBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/Kbase")
@Api(value="Knowledge Base Center")
public class KnowledgeBaseController {

	
	
	@Autowired
	private KnowledgeBaseService knowledgeBaseService;
	
	
	@ApiOperation(value="create knowledgeBase")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully created knowledge base"),
	        @ApiResponse(code = 401, message = "You are not authorized to upload the document"),
	        @ApiResponse(code = 400, message = "Bad Request")
	    })

	@PostMapping(produces= {"application/json" })
	public KnowledgeBase createKnowledgeBase(
			@ApiParam(value="KnowledgeBase Object", required = true) @RequestBody KnowledgeBase kbase){
		return knowledgeBaseService.createKnowledgeBase(kbase);
		
	}
	
	@ApiOperation(value="Retrieve all Knowledge Bases information")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved all knowledge bases information")
	    })
	@GetMapping(value="/findAll",produces= {"application/json" })
	public KnowledgeBaseWrapper getKnowledgeBases(){
		List<KnowledgeBase> knowledgeBaseList = knowledgeBaseService.getKnowledgeBases();
		
		KnowledgeBaseWrapper wrapper = new KnowledgeBaseWrapper();
		wrapper.setKnowledgeBaseList(knowledgeBaseList);
		return wrapper;
	}
	
	@ApiOperation(value="Retrieve Knowledge Base details by KnowledgeBaseId")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved the Knowledge Base")
	    })

	@GetMapping(value="/{kbaseId}",produces= {"application/json"})
	public Optional<KnowledgeBase> getKnowledgeBaseById(@PathVariable("kbaseId") ObjectId kbaseId){
		
		return knowledgeBaseService.getKnowledgeBaseById(kbaseId);
		
	}
	
	@ApiOperation(value="Update Knowledge Base details")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Updated the Knowledge Base",response=void.class)
	    })
	@ResponseStatus(HttpStatus.NO_CONTENT)

	@PutMapping
	public ResponseEntity updateKnowledgeBase(@RequestBody KnowledgeBase kbase){
		knowledgeBaseService.updateKnowledgeBase(kbase);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	@ApiOperation(value="Delete Knowledge Base by KnowledgeBaseId")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Deleted the Knowledge Base",response=void.class)
	    })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value="/{kbaseId}")
	public ResponseEntity deleteKnowledgeBase(@PathVariable("kbaseId") ObjectId kbaseId){
		knowledgeBaseService.deleteKnowledgeBase(kbaseId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
}
