package com.jdbctest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jdbctest.service.TestService;


public class JTest {
	
	@Autowired
	TestService testService;
	
	@Test
	public void testInsert() {
		testService.insertToCustomers();
	}
	

}
