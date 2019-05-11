package com.johnsonmoon.sql2excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Sql2excelApplication {
    public static void main(String[] args) {
        String workHome = System.getenv("work.home");
        if (workHome == null || workHome.isEmpty()) {
            workHome = System.getProperty("work.home");
        }
        if (workHome == null || workHome.isEmpty()) {
            workHome = System.getProperty("user.dir");
            System.setProperty("work.home", workHome);
        }
        SpringApplication.run(Sql2excelApplication.class, args);
    }
}
