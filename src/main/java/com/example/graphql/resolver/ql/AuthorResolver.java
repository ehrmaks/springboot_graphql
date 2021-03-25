package com.example.graphql.resolver.ql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.domain.repository.PostRepository;
import com.example.graphql.pojo.Author;
import com.example.graphql.pojo.Post;

import java.util.List;

public class AuthorResolver implements GraphQLResolver<Author> {

    public AuthorResolver(PostRepository postRepository) {
        super();
        this.postRepository = postRepository;
    }

    private  PostRepository postRepository;

    public List<Post> posts(Author auth) {
        return postRepository.findByAuthorId(auth.getId());
    }
}