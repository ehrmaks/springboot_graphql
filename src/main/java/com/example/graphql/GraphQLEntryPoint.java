//package com.example.graphql;
//
//
//import com.coxautodev.graphql.tools.SchemaParser;
//
//import com.example.graphql.domain.repository.UserRepository;
//import graphql.schema.GraphQLSchema;
//import graphql.servlet.SimpleGraphQLServlet;
//import org.jetbrains.annotations.NotNull;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//@CrossOrigin(origins = "*",maxAge = 3600)
//public class GraphQLEntryPoint extends SimpleGraphQLServlet {
//
//    public GraphQLEntryPoint(UserRepository userRepository) {
//        super(buildSchema(userRepository));
//    }
//
//    @NotNull
//    private static GraphQLSchema buildSchema(UserRepository userRepository ) {
//        return SchemaParser
//                .newParser()
//                .file("schema.graphqls")
//                .resolvers(
//                        new Query(postRepository,authRepository,userRepository),
//                        new Mutation(authRepository,postRepository,userRepository ),
//                        new PostResolver(authRepository),
//                        new AuthorResolver(postRepository))
//                .build()
//                .makeExecutableSchema();
//    }
//}
