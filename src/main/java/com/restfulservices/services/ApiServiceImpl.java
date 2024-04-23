package com.restfulservices.services;

import com.restfulservices.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    /*
    @Override
    public List<User> getUsers(Integer limit) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiUrl).queryParam("_limit", limit);

        //noinspection unchecked
        return restTemplate.getForObject(uriBuilder.toUriString(), List.class);

    }*/

    @Override
    public Flux<User> getUsers(Integer limit) {
        //noinspection unchecked
        return WebClient.create(apiUrl).get().uri(uriBuilder -> uriBuilder.queryParam("_limit", limit).build()).accept(MediaType.APPLICATION_JSON).exchange().flatMap(resp -> resp.bodyToMono(List.class)).flatMapIterable(list -> list);
    }


}
