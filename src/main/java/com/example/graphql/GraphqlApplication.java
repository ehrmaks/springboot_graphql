package com.example.graphql;

import com.example.graphql.domain.repository.AuthorRepository;
import com.example.graphql.domain.repository.PostRepository;
import com.example.graphql.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
@SpringBootApplication
public class GraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

    @Bean
    @Autowired
    public ServletRegistrationBean graphQLServlet(PostRepository postRepository, AuthorRepository authRepository, UserRepository userRepository ) {
        return new ServletRegistrationBean(new GraphQLEntryPoint(postRepository, authRepository,userRepository),"/graphql");
    }

}
