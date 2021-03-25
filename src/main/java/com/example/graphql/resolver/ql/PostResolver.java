package com.example.graphql.resolver.ql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.domain.repository.AuthorRepository;
import com.example.graphql.pojo.Post;


public class PostResolver implements GraphQLResolver<Post> {

    private AuthorRepository authRepository;

    public PostResolver(AuthorRepository authRepository) {
        super();
        this.authRepository = authRepository;
    }

}