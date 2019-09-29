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

- All the documents contain only text.
- Document can belong to only one category at any point of time.


**STEPS FOR IMPLEMENTING THE SOLUTION**

1.Install JAVA and mvn

2.Install Mongo DB

3.Download this project by cloning the below URL:


**Below is the list of APIs implemeted:**

1.  http://localhost:8080/Kbase  - POST
  
    This API is used to create a new knowledgebase along with the categories.
    
2.  http://localhost:8080/Kbase/findAll - GET

    This API is used to retrieve all the knowledgebases along with the category information.
    
3.  http://localhost:8080/Kbase/{kbaseId} - GET

    This API is used to get a particular knowledge base information based on knowledge base Id provided in the path variable
  
4.  http://localhost:8080/Kbase - PUT

  This API is used to update the knowledgeBase and category information.
  
5. http://localhost:8080/Kbase/{kbaseId} - DELETE

   This API is used to delete KnowledgeBase based on the given kbaseId
  
6.  http://localhost:8080/document/{kbaseId}/upload - POST

    This API is used to upload a file with documents in it. File size limit can be modified in application.properties file.
  
7.  https://localhost:8080/document/findAll/{kbaseId} - GET

   This API is used to get all the documents related to the given knowledge base.
  
8. http://localhost:8080/document/findBy/{kbaseId}/{categoryId}

   This API is used to get the documents based on the given knowledge base Id and category Id.
  
9. http://localhost:8080/document/{kbaseId} - PUT

   This API is used to update the document.
  
10. http://localhost:8080/document/{docId} - DELETE

   This API is used to delete a document
  
11. http://lcoalhost:8080/document/search/{keyword} - GET
 
  This API is used for simple text search in the documents by providing a keyword.
 
 
 
 More APIs can be added based on the requirement.
 
 Only User with AUTHOR Role can create/update the knowledge base and upload/update the document. For users with other role its read only.
 
 



 
 
 






