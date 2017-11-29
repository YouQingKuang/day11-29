package cn.com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

/*    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;*/


    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into PERSON(NAME, AGE) values(?, ?)", name, age);
 /*       jdbcTemplate1.update("insert into PERSON(NAME, AGE) values(?, ?)", name, age);
        jdbcTemplate2.update("insert into PERSON(NAME, AGE) values(?, ?)", name, age);
*/    }

    public void deleteByName(String name) {
        jdbcTemplate.update("delete from PERSON where NAME = ?", name);
/*        jdbcTemplate1.update("delete from PERSON where NAME = ?", name);
        jdbcTemplate2.update("delete from PERSON where NAME = ?", name);
*/    }

    public Integer getAllPersons() {
        return jdbcTemplate.queryForObject("select count(1) from PERSON", Integer.class);
    }

    public void deleteAllPersons() {
        jdbcTemplate.update("delete from PERSON");
/*        jdbcTemplate1.update("delete from PERSON");
        jdbcTemplate2.update("delete from PERSON");
*/    }

}
