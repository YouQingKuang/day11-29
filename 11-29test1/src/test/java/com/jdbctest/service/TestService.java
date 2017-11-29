package com.jdbctest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insertToCustomers() {
		
//		jdbcTemplate.execute("DROP TABLE IF EXISTS customers");
//        jdbcTemplate.execute("CREATE TABLE customers(" +
//                "id int(5)not null AUTO_INCREMENT," 
//        		+ "primary key (id),"
//                + "first_name VARCHAR(255),"
//                + "last_name VARCHAR(255))");
			
			List<Object[]> newData = new ArrayList<>();
			Object[] obj1 = {"88","88"};
			Object[] obj2 = {"l99isi","s9i"};
			newData.add(obj1);
			//newData.add(obj2);
			
	        // Uses JdbcTemplate's batchUpdate operation to bulk load data
			//jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES (?,?)",obj1[0]); 
	        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)",newData); 
		}
	
	}
