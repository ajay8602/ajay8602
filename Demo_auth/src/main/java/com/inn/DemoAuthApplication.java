package com.inn;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@RestController
@Slf4j
@PropertySource("file:./application.properties")
@Configuration
@ComponentScan
@Component
public class DemoAuthApplication {

        @Autowired
        private static BasicAuthClient basicAuthClient;

        public static void main(String[] args) throws IOException {
                SpringApplication.run(DemoAuthApplication.class, args);
                DemoAuthApplication dm = new DemoAuthApplication();
                basicAuthClient = new BasicAuthClient();
                dm.test();
        }

        public void test() throws IOException {
                BasicAuthClient basicAuthClient = new BasicAuthClient();
                basicAuthClient.callMethod(basicAuthClient);
        }

        @GetMapping("/healthcheck")
        public String rstCont() {
                return " Success ";
        }
}