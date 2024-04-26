package com.wsdemo.apmwebflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class Controller {
    private final WebClient client = WebClient.create();

    @GetMapping("/")
    public Mono<String> hello() {
        return client.get()
                .uri("https://www.google.com")
                .retrieve()
                .toBodilessEntity()
                .map(response -> "Response status from controller: " + response.getStatusCode());
    }
}
