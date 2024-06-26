package com.restfulservices.controllers;

import com.restfulservices.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
@Controller
public class UserController {

    private final ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, ServerWebExchange serverWebExchange) {

        Mono<MultiValueMap<String, String>> monoMap = serverWebExchange.getFormData();

        monoMap.doOnNext(map -> this.updatedModel(map, model)).subscribe();

        return "userlist";

    }

    private void updatedModel(MultiValueMap<String, String> map, Model model) {

        int limit = Integer.parseInt(map.get("limit").get(0));

        log.debug("Received Limit value: " + limit);

        if (limit == 0) {
            log.debug("Setting limit to default of 10");
            limit = 10;
        }

        model.addAttribute("users", apiService.getUsers(limit));

    }

}
