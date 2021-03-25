package com.example.graphql.resolver.root;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.example.graphql.domain.model.JwtUser;
import com.example.graphql.domain.repository.AuthorRepository;
import com.example.graphql.domain.repository.PostRepository;
import com.example.graphql.domain.repository.UserRepository;
import com.example.graphql.pojo.Author;
import com.example.graphql.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*",maxAge = 3600)
@Service
public class Mutation implements GraphQLRootResolver {


    public Mutation(AuthorRepository authRepo, PostRepository postRepo, UserRepository userRepository) {
        super();
        this.authRepo = authRepo;
        this.postRepo = postRepo;
        this.userRepository = userRepository;
    }
    @Autowired
    private AuthorRepository authRepo;
    private PostRepository postRepo;

    public Author addAuthor(String id, String authorName, String price) {
        return authRepo.save(new Author(id, authorName, price));
    }

    public Post orderProceed(String id, String orderProceed) {
        Post post = postRepo.findOne((id));
        post.setOrderProceed(orderProceed);
        return postRepo.save(post);
    }

    public Post addOrder(String orderId, String quantity, String authorId) {
        Author auth = authRepo.findOne(authorId);
        int Price = Integer.parseInt(auth.getPrice());
        if (auth != null) {

            Post post = new Post(null, quantity);
            int total = Price * Integer.parseInt(quantity);
            post.setPrice("" + total);
            post.setAuthorId(authorId);
            post.setId(orderId);
            return postRepo.save(post);
        } else
            return null;
    }

    public Author updateAuthor(String id, String authorName, String price) {
        Author author = new Author(id, authorName, price);
        author.setPrice(price);
        author.setAuthorName(authorName);
        return authRepo.save(author);
    }

    public Author deleteAuthor(String authorName) {
        Author auth = authRepo.findOne(authorName);
        authRepo.delete(authorName);
        return auth;
    }

    @Autowired
    private UserRepository userRepository;

    public JwtUser createUser(String name, String password, String email) {
        JwtUser newUser=new JwtUser();
        newUser.setName(name);
        newUser.setPasswd(password);
        newUser.setEmail(email);
        return userRepository.save(newUser);
    }
}
