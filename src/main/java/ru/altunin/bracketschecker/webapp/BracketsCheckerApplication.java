package ru.altunin.bracketschecker.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.altunin.bracketschecker")
public class BracketsCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BracketsCheckerApplication.class, args);
    }
}
