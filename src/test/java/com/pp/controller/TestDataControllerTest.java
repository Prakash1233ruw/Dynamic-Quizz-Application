package com.pp.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp.bo.User;
import com.pp.service.ITestDataService;
import com.pp.service.IUserService;

@WebMvcTest(TestDataController.class)
public class TestDataControllerTest {

  @MockBean
  private ITestDataService testDataService;

  @MockBean
  private IUserService  userService;
  @Autowired
	private MockMvc mockMvc;
  

  @Test
  public void shouldUploadFile() throws Exception {

     MockMultipartFile file = new MockMultipartFile("data", "test.txt",
      "text/plain", "Hello, World".getBytes());

    Mockito.when(testDataService.uploadTestData(ArgumentMatchers.any())).thenReturn("stored");

	  MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/uploadTestData").contentType(MediaType.MULTIPART_FORM_DATA_VALUE);


   ResultActions hh = mockMvc.perform(reqBuilder);
      //.andExpect(MockMvcResultMatchers.redirectedUrl("/home"));

       MvcResult res = hh.andReturn();
      MockHttpServletResponse response = res.getResponse();
    
      
      assertEquals(200,   response.getStatus());
      ///verify(testDataService).uploadTestData(file);
      
  }
  
  @Test
  public void userRegistration() throws Exception {
	  User user=new User(1, "2", "3", "4");
	 ObjectMapper mapper = new ObjectMapper();
	String json = mapper.writeValueAsString(user);
	
	Mockito.when(userService.userRegistration(ArgumentMatchers.any())).thenReturn(json);
	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/register");
	ResultActions result = mockMvc.perform(builder);
	MvcResult status = result.andReturn();
	MockHttpServletResponse response = status.getResponse();
  assertEquals(200, response.getStatus()) ;
  }

}

