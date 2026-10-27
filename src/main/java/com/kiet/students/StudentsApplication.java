
package com.kiet.students;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentsApplication implements CommandLineRunner {

    private static final Logger logger =
            LoggerFactory.getLogger(StudentsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("KIET Student Management System Started");
        logger.info("Loading Student Data...");
        logger.info("Student Management REST API is Ready");
    }
}
