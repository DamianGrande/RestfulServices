package com.restfulservices.services;

import com.restfulservices.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiServiceImplTest {

    @Autowired
    private ApiService apiService;

    @Before
    public void setUp() {
    }


    @Test
    public void testGetUsers() {

        List<User> users = apiService.getUsers(3).collectList().block();
        assertEquals(3, users.size());

    }

}