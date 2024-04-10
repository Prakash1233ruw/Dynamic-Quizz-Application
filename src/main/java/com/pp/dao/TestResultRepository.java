package com.pp.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pp.bo.TestResult;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer>{
 //public TestResult findByUserIdAndTestDateTime(Integer userId,LocalDateTime testDateTime);
}
