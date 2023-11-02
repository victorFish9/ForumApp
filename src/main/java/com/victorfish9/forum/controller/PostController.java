package com.victorfish9.forum.controller;

import com.victorfish9.forum.models.Post;
import com.victorfish9.forum.models.User;
import com.victorfish9.forum.repository.PostRepository;
import com.victorfish9.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@Controller
public class PostController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/home/add")
    public String postAdd(Model model){
        //User methods for getting current user_id
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        User myUser = userRepository.findByUsername(username);
        //Date methods for getting current date
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Date:" + dft.format(now));

        model.addAttribute("current_date", dft.format(now));
        model.addAttribute("myId", myUser.getId());
        model.addAttribute("post", new Post());
        return "add";
    }

    @RequestMapping(value = "/home/save", method = RequestMethod.POST)
    public String postAddMethod(@ModelAttribute Post post){
        postRepository.save(post);
        return "redirect:/home";
    }
}
