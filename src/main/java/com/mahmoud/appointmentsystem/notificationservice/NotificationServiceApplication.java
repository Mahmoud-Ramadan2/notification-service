package com.mahmoud.appointmentsystem.notificationservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationServiceApplication {

    private  static final Logger logger = LoggerFactory.getLogger(NotificationServiceApplication.class);
    public static void main(String[] args) {


        String profile = System.getProperty("spring.profiles.active","dev");

        logger.info("Active profile is: {}",profile);

        Dotenv dotenv = Dotenv.configure()
                        .filename(".%s.env".formatted(profile))
                                .ignoreIfMissing()
                                        .load();

        dotenv.entries().forEach(entry ->{
                System.out.printf("%s = %s%n",entry.getKey(),entry.getValue());
                System.setProperty(entry.getKey(),entry.getValue());});

        SpringApplication.run(NotificationServiceApplication.class, args);
    }

}
