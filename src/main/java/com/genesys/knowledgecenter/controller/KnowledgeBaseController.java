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
import org.springframework.web.bind.annotation.RestController;

import com.genesys.knowledgecenter.domain.KnowledgeBase;
import com.genesys.knowledgecenter.domain.KnowledgeBaseWrapper;
import com.genesys.knowledgecenter.service.KnowledgeBaseService;

@RestController
@RequestMapping("/Kbase")
public class KnowledgeBaseController {

	
	
	@Autowired
	private KnowledgeBaseService knowledgeBaseService;
	
	
	@PostMapping
	public KnowledgeBase createKnowledgeBase(@RequestBody KnowledgeBase kbase){
		return knowledgeBaseService.createKnowledgeBase(kbase);
		
	}
	
	@GetMapping(value="/findAll")
	public KnowledgeBaseWrapper getKnowledgeBases(){
		List<KnowledgeBase> knowledgeBaseList = knowledgeBaseService.getKnowledgeBases();
		
		KnowledgeBaseWrapper wrapper = new KnowledgeBaseWrapper();
		wrapper.setKnowledgeBaseList(knowledgeBaseList);
		return wrapper;
	}
	
	@GetMapping(value="/{kbaseId}")
	public Optional<KnowledgeBase> getKnowledgeBaseById(@PathVariable("kbaseId") ObjectId kbaseId){
		
		return knowledgeBaseService.getKnowledgeBaseById(kbaseId);
		
	}
	
	@PutMapping
	public ResponseEntity updateKnowledgeBase(@RequestBody KnowledgeBase kbase){
		knowledgeBaseService.updateKnowledgeBase(kbase);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value="/{kbaseId}")
	public ResponseEntity deleteKnowledgeBase(@PathVariable("kbaseId") ObjectId kbaseId){
		knowledgeBaseService.deleteKnowledgeBase(kbaseId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
}
