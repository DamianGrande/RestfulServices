package com.restfulservices.controllers;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest extends TestCase {

    @Autowired
    private ApplicationContext applicationContext;

    WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test
    public void testIndex() {
        webTestClient.get().uri("/").exchange().expectStatus().isOk();
    }

    @Test
    public void testFormPost() {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("limit", "3");

        webTestClient.post().uri("/users").contentType(MediaType.APPLICATION_FORM_URLENCODED).body(BodyInserters.fromFormData(formData)).exchange().expectStatus().isOk();

    }

}