package com.dolmengi.connector;

import com.dolmengi.connector.application.ConnectorClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class ConnectorApplication implements CommandLineRunner {

    private final ConnectorClient client;

    public static void main(String[] args) {
        SpringApplication.run(ConnectorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        client.run();
    }

}
