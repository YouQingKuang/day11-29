package cn.com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.example.SpringJdbcApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJdbcApplication.class)
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;
	
	@Test
	public void testCreateTable(){
		customerService.createTable();
	}
}
