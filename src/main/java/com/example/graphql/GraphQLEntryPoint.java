package com.example.graphql;


import com.coxautodev.graphql.tools.SchemaParser;

import com.example.graphql.domain.repository.AuthorRepository;
import com.example.graphql.domain.repository.PostRepository;
import com.example.graphql.domain.repository.UserRepository;
import com.example.graphql.resolver.ql.AuthorResolver;
import com.example.graphql.resolver.ql.PostResolver;
import com.example.graphql.resolver.root.Mutation;
import com.example.graphql.resolver.root.Query;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import org.jetbrains.annotations.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*",maxAge = 3600)
public class GraphQLEntryPoint extends SimpleGraphQLServlet {

    public GraphQLEntryPoint(PostRepository postRepository, AuthorRepository authRepository,UserRepository userRepository) {
        super(buildSchema(postRepository,authRepository,userRepository));
    }

    @NotNull
    private static GraphQLSchema buildSchema(PostRepository postRepository, AuthorRepository authRepository,UserRepository userRepository ) {
        return SchemaParser
                .newParser()
                .file("schema.graphqls")
                .resolvers(
                        new Query(postRepository,authRepository,userRepository),
                        new Mutation(authRepository,postRepository,userRepository ),
                        new PostResolver(authRepository),
                        new AuthorResolver(postRepository))
                .build()
                .makeExecutableSchema();
    }
}