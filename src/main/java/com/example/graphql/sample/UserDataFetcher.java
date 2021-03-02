package com.example.graphql.sample;

import com.example.graphql.domain.repository.UserRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataFetcher {
    @Autowired
    private UserRepository userRepository;

    public DataFetcher<?> allUsers() {
        return environment -> {
            return userRepository.findAll();
        };
    }

    public DataFetcher<?> user() {
        return environment -> {
            int id = environment.getArgument("user_id");
            System.out.println(id + "======================");
            return userRepository.findById(id);
        };
    }
}
