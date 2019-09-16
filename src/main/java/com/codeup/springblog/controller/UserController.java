package com.codeup.springblog.controller;

import com.codeup.springblog.model.Post;
import com.codeup.springblog.model.User;
import com.codeup.springblog.repo.PostRepo;
import com.codeup.springblog.repo.UsersRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;


@Controller
public class UserController {
    private UsersRepo users;
    private final PostRepo postDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UsersRepo users, PasswordEncoder passwordEncoder, PostRepo postDao) {
        this.users = users;
        this.postDao = postDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "/users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user,Model model){
        if(
//            user.getPhoto().equals("") ||
            user.getUsername().equals("") ||
            user.getPassword().equals("") ||
            user.getEmail().equals("")){
            model.addAttribute("usercache",user);
            return "/users/sign-up";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }
    @GetMapping("/profile")
    public String viewProfile(Model model){
        Iterable<Post> posts = postDao.findAll();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArrayList<Post> formatedPosts = new ArrayList<>();
        for(Post post : posts) {
            Post newPost = new Post(post);
            if(newPost.getBody().length() >= 200){
                newPost.setBody(newPost.getBody().substring(0,200) +"...");
            }
            formatedPosts.add(newPost);
        }
        Iterable<Post> formatedResults = formatedPosts;
        model.addAttribute("posts", formatedResults);
        model.addAttribute("user",user);
        return "Users/profile";
//        Iterable<Post> userPosts = postDao.findAll(posts, user.getId());
    }


}