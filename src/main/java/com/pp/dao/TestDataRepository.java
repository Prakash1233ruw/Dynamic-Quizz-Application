package com.pp.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pp.bo.TestData;
@Repository
public interface TestDataRepository extends JpaRepository<TestData, Integer> {
}
