package com.wjx.test;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Author WangJX
 * @Date 2023/6/28 15:41
 * @Description
 */
public class Main {
    public static void main(String[] args) {


    }
    WebClient webClient = WebClient.create("http://localhost:8080");
    public Mono<User> findById(Long userId) {
        return webClient
                .get()
                .uri("/users/" + userId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(cr -> cr.bodyToMono(User.class));
    }
}
