package com.wsdemo.apmwebflux;

import co.elastic.apm.api.ElasticApm;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class Filter implements WebFilter {
    private final WebClient client = WebClient.create();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var currentTransaction = ElasticApm.currentTransaction(); // This is NoopTransaction

        var newTransaction = ElasticApm.startTransaction();
        return client.get()
                .uri("https://www.google.com")
                .retrieve()
                .toBodilessEntity()
                .doOnNext(response -> System.out.println("Response status from filter: " + response.getStatusCode()))
                .then(chain.filter(exchange))
                .doOnTerminate(newTransaction::end);
    }
}
