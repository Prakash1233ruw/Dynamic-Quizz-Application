package com.pp.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pp.bo.TestData;
import com.pp.bo.TestResult;
import com.pp.bo.User;
import com.pp.dao.TestDataRepository;
import com.pp.dao.TestResultRepository;
import com.pp.dao.UserRepository;
import com.pp.exception.TestDataNotFoundException;
import com.pp.exception.TestResultsNotFoundException;

@Service
public class TestDataServiceImpl implements ITestDataService{

	@Autowired
    private	TestDataRepository repository;
	
	@Autowired
	private UserServiceImpl userService;
	
	

	@Autowired
	private TestResultRepository testResultRepository;
	@Override
	public String uploadTestData(MultipartFile file) {
	    try (InputStream inputStream = file.getInputStream()) {
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheetAt(0); // Assuming test data is in the first sheet

	        Iterator<Row> rowIterator = sheet.iterator();
	        // Skipping header row
	        if (rowIterator.hasNext()) {
	            rowIterator.next();
	        }

	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            TestData testData = new TestData();
	            testData.setId((int) row.getCell(0).getNumericCellValue());
	            testData.setQuestion(getStringValue(row.getCell(1)));
	            testData.setOption1(getStringValue(row.getCell(2)));
	            testData.setOption2(getStringValue(row.getCell(3)));
	            testData.setOption3(getStringValue(row.getCell(4)));
	            testData.setOption4(getStringValue(row.getCell(5)));
	            testData.setNextQuestionOption1((int) row.getCell(6).getNumericCellValue());
	            testData.setNextQuestionOption2((int) row.getCell(7).getNumericCellValue());
	            testData.setNextQuestionOption3((int) row.getCell(8).getNumericCellValue());
	            testData.setNextQuestionOption4((int) row.getCell(9).getNumericCellValue());
 
	            repository.save(testData);
	        }
	    }
	    catch (IOException e) {
			throw new TestDataNotFoundException("Problem with Paraing the data");
		}
	    
	    return "test data uploaded";
	}

	private String getStringValue(Cell cell) {
	    if (cell != null) {
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                return String.valueOf((int) cell.getNumericCellValue());
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            default:
	                return null;
	        }
	    }
	    return null;
	}

	@Override
	    public TestData getQuestionById(Integer id) {
	        return repository.findById(id).orElse(null);
	    }

	    public TestData getNextQuestion(Integer currentQuestionId, Integer selectedOption) {
	    	
	        TestData currentQuestion = repository.findById(currentQuestionId).orElse(null);
	        if (currentQuestion == null) {
	            return null;
	        }
	        Integer nextQuestionId = null;
	        switch (selectedOption) {
	            case 1:
	                nextQuestionId = currentQuestion.getNextQuestionOption1();
	                break;
	            case 2:
	                nextQuestionId = currentQuestion.getNextQuestionOption2();
	                break;
	            case 3:
	                nextQuestionId = currentQuestion.getNextQuestionOption3();
	                break;
	            case 4:
	                nextQuestionId = currentQuestion.getNextQuestionOption4();
	                break;
	            default:
	               System.out.println("not matched");
	                break;
	        }
	        
	        
	        TestResult testResult= new TestResult(currentQuestion.getQuestion(),selectedOption);
	    
            //testResult.setUser(userService.findUser());
             testResultRepository.save(testResult);
	        return repository.findById(nextQuestionId).orElse(null);
	    }
	    
//	    
//	    public static void SaveResults(String question,Integer selectedOption,User user) {
//	    	// TestResult testResult= new TestResult(question,selectedOption,user);
//	    	 testResultRepository.save(null);
//	    
//	    }
	    
		
	    @Override
		public TestResult getTestResults(Integer id)  {
			Optional<TestResult> optional = testResultRepository.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}
			throw new TestResultsNotFoundException("no results found with id"+id); 
			
		}
	    
	    @Override
		public List<TestResult> getAllTestResults(){
		return	testResultRepository.findAll();
		}
//	    
//	    public TestResult getUserTestResultById(Integer userId, LocalDateTime testDateTime) {
//	        return testResultRepository.findByUserIdAndTestDateTime(userId, testDateTime);
//	    }
//	    
//	    public List<TestResult> getAllTestResults(int page, int size) {
//	        Pageable pageable = PageRequest.of(page, size, Sort.by("testDateTime").descending());
//	        return testResultRepository.findAll(pageable).getContent();
//	    }
	    
	    
	}

	

