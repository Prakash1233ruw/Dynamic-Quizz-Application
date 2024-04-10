package com.pp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pp.bo.TestData;
import com.pp.bo.TestResult;

public interface ITestDataService {
	  public String uploadTestData(MultipartFile file);
	  public TestData getQuestionById(Integer id);
	  public TestData getNextQuestion(Integer currentQuestionId, Integer selectedOption);
	  public TestResult getTestResults(Integer id) ;
	  public List<TestResult> getAllTestResults();
//	  public List<TestResult> getAllTestResults(int page, int size);
//	  public TestResult getUserTestResultById(Integer userId, LocalDateTime testDateTime);
}
