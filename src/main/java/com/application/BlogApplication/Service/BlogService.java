package com.application.BlogApplication.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.BlogApplication.Entity.BlogPost;
import com.application.BlogApplication.Entity.Comment;
import com.application.BlogApplication.Entity.UserEntity;
import com.application.BlogApplication.Repository.BlogRepository;

@Service
public class BlogService {
	
	@Autowired
    private BlogRepository blogPostRepository;

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAllBlogPostsWithComments();
    }

    public BlogPost createBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public List<Comment> getCommentsForBlogPost(Long postId) {
        BlogPost blogPost = blogPostRepository.findBlogPostWithCommentsById(postId);
        if (blogPost != null) {
            return blogPost.getComments();
        }
        return null;
    }

    public BlogPost addCommentToBlogPost(Long postId, Comment comment) {
        BlogPost blogPost = blogPostRepository.findBlogPostWithCommentsById(postId);
        if (blogPost != null) {
            blogPost.addComment(comment);
            return blogPostRepository.save(blogPost);
        }
        return null;
    }

    public List<BlogPost> getBlogPostsByUser(UserEntity user) {
        return blogPostRepository.findByUser(user);
    }
}
