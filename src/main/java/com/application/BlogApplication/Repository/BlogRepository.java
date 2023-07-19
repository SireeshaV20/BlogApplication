package com.application.BlogApplication.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.BlogApplication.Entity.BlogPost;
import com.application.BlogApplication.Entity.UserEntity;

@Repository
public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    @Query("SELECT b FROM BlogPost b JOIN b.comments WHERE b.id = :id")
    BlogPost findBlogPostWithCommentsById(Long id);

    @Query("SELECT DISTINCT b FROM BlogPost b JOIN b.comments")
    List<BlogPost> findAllBlogPostsWithComments();
    
    List<BlogPost> findByUser(UserEntity user);
}
