package com.lambdaschool.todos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Starting class for testing
 */
// @EnableJpaAuditing
@SpringBootApplication
public class TodosApplicationTests {

    /**
     * Main method to start the application.
     *
     * @param args Not used in this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(TodosApplication.class,
                args);
    }
}
