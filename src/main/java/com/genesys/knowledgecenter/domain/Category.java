package com.genesys.knowledgecenter.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Category {
	
	@Id
	private ObjectId id;
	private String name;
	
	public  Category() {
		// TODO Auto-generated constructor stub
	}
	

    @PersistenceConstructor
    public Category(ObjectId id, String name){
            super();
            this.id = id;
            this.name = name;
    }

	

	public String getId() {
		return id.toHexString();
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	

}
