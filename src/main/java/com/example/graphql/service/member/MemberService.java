package com.example.graphql.service.user;

import com.example.graphql.dataFetcher.UserDataFetcher;
import com.example.graphql.domain.repository.UserRepository;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDataFetcher userDataFetcher;

    @Value("classpath:graphql/userSchema.graphqls")
    Resource resource;

    private GraphQL graphQL;

    @PostConstruct
    public void init() throws IOException {
        URL url = resource.getURL();
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        System.out.println("여기ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ");
        return RuntimeWiring.newRuntimeWiring()
                .type(
                        TypeRuntimeWiring
                                .newTypeWiring("Query")
                                .dataFetcher("allUsers", userDataFetcher.allUsers())
                                .dataFetcher("user", userDataFetcher.user())
                )
                .build();
    }
}
