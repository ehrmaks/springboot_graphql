package com.example.graphql.resolver.root;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.example.graphql.domain.model.JwtUser;
import com.example.graphql.domain.repository.AuthorRepository;
import com.example.graphql.domain.repository.PostRepository;
import com.example.graphql.domain.repository.UserRepository;
import com.example.graphql.pojo.Author;
import com.example.graphql.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/rest/graphql")
public class Query implements GraphQLRootResolver {


    public Query(PostRepository postRepository, AuthorRepository authRepo, UserRepository userRepository) {
        super();
        this.postRepository = postRepository;
        this.authRepo = authRepo;
        this.userRepository=userRepository;
    }
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private  AuthorRepository authRepo;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/getProlist")
    public List<Post> allOrders() {
        return postRepository.findAll();
    }

    @GetMapping("/list")
    public List<Author> allAuthors() {
        return authRepo.findAll();
    }

    public Author getAuthor(String Id){
        Author a = authRepo.findById(Id);
        return a;
    }

    public JwtUser loginUser(String userName){
        JwtUser dbUser=userRepository.findByUserName(userName);
//		System.out.println(dbUser.getName());
        return dbUser;
    }

    public List<JwtUser> allUsers() {
        return userRepository.findAll();
    }

}