# knowledgecenter

**OVERVIEW:**

Knowledge center has sub categories and each category has documents associated with it.

Goal is to develop REST APIs to create knowledge center,categories and associate documents with them.

**TECHNOLOGIES USED:**

JAVA

Embedded Mongo DB

Spring Boot

Maven as build tool


**ASSUMPTIONS:**

- All the documents contain only text.
- Document can belong to only one category at any point of time.


**STEPS FOR IMPLEMENTING THE SOLUTION**

1.Install JAVA and mvn.

2.Download this project by cloning the below URL:

https://github.com/shruthipodduturi/knowledgecenter.git


**Below is the list of APIs implemeted:**

1.  curl -u username:password -X POST "http://localhost:8080/Kbase" -H "accept: application/json" -H "Content-Type: application/json" 
  
    This API is used to create a new knowledgebase along with the categories.Only user with Role AUTHOR can access this URL.
    
2.  curl -X GET "http://localhost:8080/Kbase/findAll" -H "accept: application/json"

    This API is used to retrieve all the knowledgebases along with the category information.
    
3.  curl -X GET "http://localhost:8080/Kbase/{kbaseId}" -H "accept: application/json"

    This API is used to get a particular knowledge base information based on knowledge base Id provided in the path variable
  
4.  curl -u username:password -X PUT "http://localhost:8080/Kbase" -H "accept: */*" -H "Content-Type: application/json" 

    This API is used to update the knowledgeBase and category information.Only user with ROLE AUTHOR can access this URL.
  
5.  curl -u username:password -X DELETE "http://localhost:8080/Kbase/{kbaseId}" -H "accept: */*"

    This API is used to delete KnowledgeBase based on the given kbaseId,Only user with ROLE AUTHOR can access this URL.
  
6.  curl -u username:password -X POST "http://localhost:8080/document/sdsd/upload" -H "accept: application/json" -H "Content-Type: multipart/form-data" -F "file=@document.json;type=application/json"

    This API is used to upload a file with documents in it. File size limit can be modified in application.properties file.
    Only user with ROLE AUTHOR can upload the documents.
  
7.  curl -X GET "http://localhost:8080/document/findAll/{kbaseId}" -H "accept: application/json"

    This API is used to get all the documents related to the given knowledge base.
  
8.  curl -X GET "http://localhost:8080/document/findBy/{kbaseId}/{categoryId}" -H "accept: application/json"

    This API is used to get the documents based on the given knowledge base Id and category Id.
  
9.  curl -u username:password -X PUT "http://localhost:8080/document/{kbaseId}" -H "accept: */*" -H "Content-Type: application/json" -d 

    This API is used to update the document.Only user with ROLE AUTHOR can update the document.
  
10. curl -u username:password -X DELETE "http://localhost:8080/document/{docId}" -H "accept: */*"

    This API is used to delete a document,Only user with ROLE AUTHOR can delete the document.
  
11. curl -X GET "http://localhost:8080/document/search/{keyword}" -H "accept: application/json"
 
    This API is used for simple text search in the documents by providing a keyword.
 
 
 
 More APIs can be added based on the requirement.
 
 Only User with AUTHOR Role can create/update the knowledge base and upload/update the document. For users with other role its read only.
 
 



 
 
 






