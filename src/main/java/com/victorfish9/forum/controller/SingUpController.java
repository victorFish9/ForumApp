package com.victorfish9.forum.controller;

import com.victorfish9.forum.models.SignUpForm;
import com.victorfish9.forum.models.User;
import com.victorfish9.forum.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class SingUpController {
    @Autowired
    private UserRepository repo;

    @GetMapping(value = "/signup")
    public String registrationForm(Model model){
        model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registrationSubmit(@Valid @ModelAttribute("signupform") SignUpForm signUpForm, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            if(signUpForm.getPassword().equals(signUpForm.getPasswordRepeat())){
                String pwd = signUpForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String repPwd = bc.encode(pwd);

                User newUser = new User();
                newUser.setPassword(repPwd);
                newUser.setUsername(signUpForm.getUsername());
                newUser.setFirstname(signUpForm.getFirstname());
                newUser.setRole("USER");
                if (repo.findByUsername(signUpForm.getUsername()) == null){
                    repo.save(newUser);
                    return "redirect/login";
                } else {
                    bindingResult.rejectValue("username", "err.username", "username already exists");
                }
            }else{
                bindingResult.rejectValue("passwordRepeat", "err.passRep", "Passwords does not match");
                return "signup";
            }
        }
        return "redirect:/login";
    }
}
