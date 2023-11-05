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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/home")
    public String home(Model model){

        return "home";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
