package com.genesys.knowledgecenter.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.genesys.knowledgecenter.KnowledgecenterApplication;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes=KnowledgecenterApplication.class)
public class DocumentControllerIntegrationTests {
	
	@Autowired
	private MockMvc mvc;
	
	
	@Test
	@WithMockUser(roles="AUTHOR")
	public void uploadFile_success_thenReturnStatus200() throws Exception {
		
		FileInputStream inputFile = new FileInputStream("src/test/resources/document.json");
		
		MockMultipartFile multipartFile = new MockMultipartFile("file","document.json",
				"multipart/form-data",inputFile);
		
		mvc.perform(multipart("/document/5da80ad619cef7d82c670692/upload")
				.file(multipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

    @Test
    @WithMockUser(roles="AUTHOR")
    public void uploadFile_Missing_thenReturnStatus404() throws Exception {

    	FileInputStream inputFile = new FileInputStream("src/test/resources/emptyfile.json");
		
		MockMultipartFile multipartFile = new MockMultipartFile("file","emptyfile.json",
				"multipart/form-data",inputFile);
		
    	
        this.mvc.perform(multipart("/document/5da80ad619cef7d82c670692/upload")
				.file(multipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("File Not Found"));
    }

    
    @Test
    @WithMockUser(roles="AUTHOR")
    public void uploadFile_InvalidDocumentType_thenReturnStatus400() throws Exception {

    	FileInputStream inputFile = new FileInputStream("src/test/resources/invalidDocType.json");
		
		MockMultipartFile multipartFile = new MockMultipartFile("file","invalidDocType.json",
				"multipart/form-data",inputFile);
		
    	
        this.mvc.perform(multipart("/document/5da80ad619cef7d82c670692/upload")
				.file(multipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Invalid Document Type"));
    }
    
    @Test
    public void uploadFile_NotAuthor_thenReturnStatus401() throws Exception {

    	FileInputStream inputFile = new FileInputStream("src/test/resources/document.json");
		
		MockMultipartFile multipartFile = new MockMultipartFile("file","document.json",
				"multipart/form-data",inputFile);
		
    	
        this.mvc.perform(multipart("/document/5da80ad619cef7d82c670692/upload")
				.file(multipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    
    @Test
    @WithMockUser(roles="AUTHOR")
    public void uploadFile_InvalidText_thenReturnStatus400() throws Exception {

    	FileInputStream inputFile = new FileInputStream("src/test/resources/invalidText.json");
		
		MockMultipartFile multipartFile = new MockMultipartFile("file","invalidText.json",
				"multipart/form-data",inputFile);
		
    	
        this.mvc.perform(multipart("/document/5da80ad619cef7d82c670692/upload")
				.file(multipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Incorrect Text Format"));
    }
	
	

}
