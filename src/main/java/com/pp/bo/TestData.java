package com.pp.bo;



import jakarta.persistence.Entity;

import jakarta.persistence.Id;




@Entity
public class TestData {
    @Id
    private Integer id;

    
    private String question;

   
    private String option1;

 
    private String option2;

   
    private String option3;

    
    private String option4;

   
    private Integer nextQuestionOption1;

  
    private Integer  nextQuestionOption2;

    
    private Integer nextQuestionOption3;

   
    private Integer nextQuestionOption4;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public Integer getNextQuestionOption1() {
		return nextQuestionOption1;
	}

	public void setNextQuestionOption1(Integer nextQuestionOption1) {
		this.nextQuestionOption1 = nextQuestionOption1;
	}

	public Integer getNextQuestionOption2() {
		return nextQuestionOption2;
	}

	public void setNextQuestionOption2(Integer nextQuestionOption2) {
		this.nextQuestionOption2 = nextQuestionOption2;
	}

	public Integer getNextQuestionOption3() {
		return nextQuestionOption3;
	}

	public void setNextQuestionOption3(Integer nextQuestionOption3) {
		this.nextQuestionOption3 = nextQuestionOption3;
	}

	public Integer getNextQuestionOption4() {
		return nextQuestionOption4;
	}

	public void setNextQuestionOption4(Integer nextQuestionOption4) {
		this.nextQuestionOption4 = nextQuestionOption4;
	}

	public TestData() {
		System.out.println("TestData instantiated");
	}

	@Override
	public String toString() {
		return "TestData [id=" + id + ", question=" + question + ", option1=" + option1 + ", option2=" + option2
				+ ", option3=" + option3 + ", option4=" + option4 + ", nextQuestionOption1=" + nextQuestionOption1
				+ ", nextQuestionOption2=" + nextQuestionOption2 + ", nextQuestionOption3=" + nextQuestionOption3
				+ ", nextQuestionOption4=" + nextQuestionOption4 + "]";
	}


}
