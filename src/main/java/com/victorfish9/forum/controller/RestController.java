package com.victorfish9.forum.controller;


import com.victorfish9.forum.models.Post;
import com.victorfish9.forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/")
public class RestController {

    @Autowired
    private PostRepository postRepository;

    //REST services
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public @ResponseBody List<Post> postsListRest(){
        return (List<Post>) postRepository.findAll();
    }
/*
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Post> postListRest(@PathVariable("id") Long id){
        return postRepository.findById(id);
    }*/

}
