package ood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Hello world!

 */
@SpringBootApplication
@EnableScheduling
public class ApplicationBoot {
    public static void main( String[] args ) {
        SpringApplication.run(ApplicationBoot.class, args);
    }
}
