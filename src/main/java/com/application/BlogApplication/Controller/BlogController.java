package com.application.BlogApplication.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.BlogApplication.Entity.BlogPost;
import com.application.BlogApplication.Entity.Comment;
import com.application.BlogApplication.Entity.UserEntity;
import com.application.BlogApplication.Service.BlogService;
import com.application.BlogApplication.Service.UserService;

@RestController
@RequestMapping("/api")
public class BlogController {
	
	@Autowired
    private  BlogService blogService;
	
	@Autowired
    private  UserService userService; 

    @GetMapping("/blogposts")
    public List<BlogPost> getAllBlogPosts() {
        return blogService.getAllBlogPosts();
    }

    @PostMapping("/blogposts")
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost) {
        return blogService.createBlogPost(blogPost);
    }

    @GetMapping("/blogposts/{id}/comments")
    public List<Comment> getCommentsForBlogPost(@PathVariable Long id) {
        return blogService.getCommentsForBlogPost(id);
    }

    @PostMapping("/blogposts/{id}/comments")
    public BlogPost addCommentToBlogPost(@PathVariable Long id, @RequestBody Comment comment) {
        return blogService.addCommentToBlogPost(id, comment);
    }

    @GetMapping("/users/{username}/blogposts")
    public List<BlogPost> getBlogPostsByUser(@PathVariable String username) {
        UserEntity user = userService.findUserByUsername(username);
        if (user != null) {
            return blogService.getBlogPostsByUser(user);
        }
        throw new IllegalArgumentException("User not found.");
    }
}
