package com.jdbctest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jdbctest.service.TestService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJdbcApplication.class)
public class TestJdbcApplicationTests {

	@Autowired
	TestService testService;
	
	@Test
	public void testInsert() {
		testService.insertToCustomers();
	}

}
