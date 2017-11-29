package cn.com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class SpringJdbcApplication implements CommandLineRunner{
  private static final Logger log = LoggerFactory.getLogger(SpringJdbcApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SpringJdbcApplication.class, args);
  }

 @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {
    log.info("Creating tables");
    jdbcTemplate.execute("DROP TABLE IF EXISTS actor");
    jdbcTemplate.execute("CREATE TABLE actor(" +
            "id int(5)not null AUTO_INCREMENT," 
    		+ "primary key (id),"
            + "first_name VARCHAR(255),"
            + "last_name VARCHAR(255),"
    		+ "birth_date TIMESTAMP)");
  }
  /*
    // Split up the array of whole names into an array of first/last names
    List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
        .map(name -> name.split(" "))
        .collect(Collectors.toList());

    // Use a Java 8 stream to print out each tuple of the list
    splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

    // Uses JdbcTemplate's batchUpdate operation to bulk load data
    jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

    log.info("Querying for customer records where first_name = 'Josh':");
    jdbcTemplate.query(
        "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[]{"Josh"},
        (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
    ).forEach(customer -> log.info(customer.toString()));
  }*/
}

