package com.victorfish9.forum.controller;

import com.victorfish9.forum.models.Post;
import com.victorfish9.forum.models.User;
import com.victorfish9.forum.repository.PostRepository;
import com.victorfish9.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/home")
    public String home(Model model){
        //last 3 post
        List<Post> allPosts = (List<Post>) postRepository.findAll();
        List<Post> latestPosts = allPosts.subList(Math.max(0, allPosts.size() - 3), allPosts.size());
        Collections.sort(latestPosts, Collections.reverseOrder(Comparator.comparing(Post::getDate)));
        //user details for navbar
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        User myUser = userRepository.findByUsername(username);

        model.addAttribute("myId", myUser.getId());
        model.addAttribute("myUser", myUser.getUsername());
        model.addAttribute("latestPosts", latestPosts);
        return "home";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
