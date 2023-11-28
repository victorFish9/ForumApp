package com.victorfish9.forum;

import com.victorfish9.forum.models.Post;

import com.victorfish9.forum.repository.PostRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void testSavePost(){
        Post post = new Post();
        postRepository.save(post);
        assertNotNull(post.getId());
    }

    @Test
    public void testFindAllPosts(){
        Iterable<Post> posts = postRepository.findAll();
        System.out.println(posts);
        List<Post> postList = new ArrayList<>();
        posts.forEach(postList::add);
        assertEquals(6, postList.size());
    }
}
