package com.dolmengi.connector;

import com.dolmengi.connector.application.ConnectorClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@RequiredArgsConstructor
@EnableFeignClients
@SpringBootApplication
public class ConnectorApplication implements CommandLineRunner {

    private final ConnectorClient client;

    static void main(String[] args) {
        SpringApplication.run(ConnectorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        client.run();
    }

}
