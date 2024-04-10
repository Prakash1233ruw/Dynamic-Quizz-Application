package com.pp.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pp.bo.TestData;
import com.pp.bo.TestResult;
import com.pp.bo.User;
import com.pp.service.ITestDataService;
import com.pp.service.IUserService;

@Controller

public class TestDataController {

    @Autowired
    private ITestDataService testDataService;
    @Autowired
    private IUserService userService;
    
    @GetMapping("/register")
    public String showregistrationForm(Model model) {
    	model.addAttribute("user", new User());
    	return "UserDetails";
    }
  
    @PostMapping("/register")
    public String ProcessRegistrationForm(@ModelAttribute User user) {
    	userService.userRegistration(user);
    	return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String index() {
        return "index"; 
    }
    
    
    @GetMapping("/uploadTestData")
    public String uploadTestData() {
        return "uploadTestData"; 
    }
    
    @PostMapping("/uploadTestData")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
           testDataService.uploadTestData(file);
            return "redirect:/home"; // Redirect after successful upload
        } else {
            return "redirect:/uploadTestData?error=empty"; // Redirect with error parameter if file is empty
        }
    }
    @GetMapping("/question")
    public String getInitialQuestion(Model model) {
        TestData initialQuestion = testDataService.getQuestionById(1); // Assuming you have a method to get the initial question
        if (initialQuestion != null) {
            model.addAttribute("question", initialQuestion);
            return "question"; // This maps to question.html
        } else {
            return "error"; // Handle error appropriately
        }
    }
    @GetMapping("/question/{questionId}")
    public String getQuestion(@PathVariable Integer questionId, Model model) {
        TestData question = testDataService.getQuestionById(questionId);
        if (question != null) {
            model.addAttribute("question", question);
            return "question"; // This maps to question.html
        } else {
            return "error"; // Handle error appropriately
        }
    }

    @PostMapping("/answer")
    public String postAnswer(@RequestParam Integer questionId, @RequestParam Integer selectedOption) {
        TestData nextQuestion = testDataService.getNextQuestion(questionId, selectedOption);
        if (nextQuestion != null) {
        	
            return "redirect:/question/" + nextQuestion.getId(); // Redirect to the next question
        } else {
        	
            return "completed"; // Handle error appropriately
        }
    }
    
    @GetMapping("/findResults")
    public String getTestResult( Model model) {
    
            model.addAttribute("testResult",  new TestResult());
      
            return "testResult1"; 
        }
    
    @GetMapping("/findTestResultsById")
    public String getTestResultById(@RequestParam("userId") Integer id,@ModelAttribute TestResult testResult,Model model) {
                 TestResult testResultData = testDataService.getTestResults(id);
        if (testResultData != null) {
            model.addAttribute("testResult", testResultData);
            return "testResultList"; // This maps to question.html
        } else {
            return "error"; // Handle error appropriately
        }
    }
    
    
    @GetMapping("/viewAllTestResults")
    public String getAllTestResult(Model model) {
              List<TestResult> testResultList = testDataService.getAllTestResults();
        if (testResultList != null) {
            model.addAttribute("testResult", testResultList);
            return "testResultList"; // This maps to question.html
        } else {
            return "error"; // Handle error appropriately
        }
    }
   
}
