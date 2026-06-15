package com.alexey.order.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<String> users = new ArrayList<>();


    public void createUser(String username) {
        users.add(username);
    }

    @CacheEvict(value = "users", key = "#id")
    public void removeUser(int id) {
        users.remove(id);

    }

    @Cacheable(value = "users", key = "#id")
    public String getUser(int id) {
        try {
            Thread.sleep(6000);
        }catch(InterruptedException exception) {

        }
        return users.get(id);
    }


}
