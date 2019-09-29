# knowledgecenter

**OVERVIEW:**

Knowledge center has sub categories and each category has documents associated with it.

Goal is to develop REST APIs to create knowledge center,categories and associate documents with them.

**TECHNOLOGIES USED:**

JAVA

Mongo DB

Spring Boot

Maven as build tool


**ASSUMPTIONS:**

All the documents contain only text.
and document can belong to only one category at any point of time.


**STEPS FOR IMPLEMENTING THE SOLUTION**

1.Install JAVA and mvn

2.Install Mongo DB

3.Download this project by cloning the below URL:


**Below is the list of APIs implemeted:**

1.  https://localhost:8080/Kbase  - POST
  
    This API is used to create a new knowledgebase along with the categories
    
    
2.  https://localhost:8080/Kbase/findAll - GET

    This API is used to retrieve all the knowledgebases along with the category information.
    
3.  https://localhost:8080/Kbase/{kbaseId} - GET

    This API is used to get a particular knowledge base information based on knowledge base Id provided in the path variable
  
  
4.  https://localhost:8080/document/{kbaseId}/upload - POST

    This API is used to upload a file with documents in it.
  
5.  https://localhost:8080/document/findAll/{kbaseId} - GET

  This API is used to get all the documents related to the given knowledge base.
  
6. https://localhost:8080/document/findBy/{kbaseId}/{categoryId}

   This API is used to get the documents based on the given knowledge base Id and category Id.
  
7. https://localhost:8080/document/{kbaseId} - PUT

   This API is used to update the document.
  
8. https://localhost:8080/document/{docId} - DELETE

   This API is used to delete a document
  
9. https://lcoalhost:8080/document/search/{keyword} - GET
 
  This API is used for simple text search in the documents by providing a keyword.
 
 
 
 More APIs can be added based on the requirement.
 
 Only User with AUTHOR Role can create the knowledge base and upload/update the document. For users with other role its read only.



 
 
 






