package com.victorfish9.forum.controller;

import com.victorfish9.forum.models.Post;
import com.victorfish9.forum.models.User;
import com.victorfish9.forum.repository.PostRepository;
import com.victorfish9.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


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
        return "redirect:/feed";
    }

    @RequestMapping(value = "/feed")
    public String postFeed(Model model){
        //Getting user ID
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        User myUser = userRepository.findByUsername(username);
        //Sorting posts from newest to oldest
        List<Post> posts = (List<Post>) postRepository.findAll();
        Collections.sort(posts, Collections.reverseOrder(Comparator.comparing(Post::getDate)));

        model.addAttribute("posts", posts);
        model.addAttribute("myId", myUser.getId());
        model.addAttribute("myUser", myUser.getUsername());
        return "feed";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") Long id){
        postRepository.deleteById(id);
        return "redirect:../feed";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editPost(@PathVariable("id")Long id, Model model){
        //Date methods for getting current date
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Date:" + dft.format(now));
        //User methods for getting current user_id
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        User myUser = userRepository.findByUsername(username);

        model.addAttribute("myId", myUser.getId());
        model.addAttribute("current_date", dft.format(now));
        model.addAttribute("post", postRepository.findById(id));
        return "editpost";
    }

    @RequestMapping(value = "/show/{id}")
    public String showPost(@PathVariable("id") Long id, Model model){
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);

        model.addAttribute("post", res);
        return "showpost";
    }

    @RequestMapping(value = "/user/{id}")
    public String userDetail(@PathVariable("id") Long id, Model model){
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res :: add);
        model.addAttribute("users", res);
        return "userDetail";
    }




}
