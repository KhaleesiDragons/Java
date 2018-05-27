package com.springboot;

import com.springboot.model.Person;
import com.springboot.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    JdbcTemplate jdbcTemplate;
    // PersonRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        jdbcTemplate.execute("DROP TABLE person IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE PERSON( id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255))");
    }

}
