package com.pp.bo;



import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
@Entity
@Table(name = "testResult")
public class TestResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ResultId")
	 private Integer resultId;
	    private String question;
	    private Integer selectedOption;
	  
	    private LocalDateTime dateTime;
	    @OneToOne(cascade = CascadeType.ALL,targetEntity = User.class)
	    private User user;
	    
	    public User getUser() {
			return user;
		}



		public void setUser(User user) {
			this.user = user;
		}



		public TestResult( String question, Integer selectedOption) {
			super();
			
			this.question = question;
			this.selectedOption = selectedOption;
			this.dateTime = LocalDateTime.now();
		
		}



		public LocalDateTime getDateTime() {
			return dateTime;
		}



		public void setDateTime() {
			this.dateTime = LocalDateTime.now();;
		}



		

		public Integer getResultId() {
			return resultId;
		}



		public void setResultId(Integer resultId) {
			this.resultId = resultId;
		}



	
		public String getQuestion() {
			return question;
		}



		public void setQuestion(String question) {
			this.question = question;
		}



		public Integer getSelectedOption() {
			return selectedOption;
		}



		public void setSelectedOption(Integer selectedOption) {
			this.selectedOption = selectedOption;
		}


		public TestResult() {
			System.out.println("TestResult Instantiated");
		}

		
}
