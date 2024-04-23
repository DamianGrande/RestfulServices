package com.restfulservices;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestTemplateExamples {

    private static final String API_ROOT = "https://api.predic8.de/shop/v2";

    @Test
    public void getCustomers() {

        String apiUrl = API_ROOT + "/customers/";

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode = restTemplate.getForObject(apiUrl, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode);

    }

    @Test
    public void createCustomer() {

        String apiUrl = API_ROOT + "/customers/";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> postMap = new HashMap<>();
        postMap.put("firstName", "Joe");
        postMap.put("lastName", "Buck");

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode);

    }

}
