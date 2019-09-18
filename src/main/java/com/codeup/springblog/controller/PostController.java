package com.codeup.springblog.controller;

import com.codeup.springblog.model.Photo;
import com.codeup.springblog.model.Post;
import com.codeup.springblog.model.User;
import com.codeup.springblog.repo.PhotoRepo;
import com.codeup.springblog.repo.PostRepo;
import com.codeup.springblog.repo.UsersRepo;
import com.codeup.springblog.services.EmailService;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;


@Controller
public class PostController{

    private final PostRepo postDao;
    private final UsersRepo userDao;
    private final EmailService emailService;
    private final PhotoRepo photoDao;

    public PostController(PostRepo postDao, UsersRepo userRepo, EmailService emailService,PhotoRepo photoDao) {
        this.userDao = userRepo;
        this.postDao = postDao;
        this.emailService = emailService;
        this.photoDao = photoDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postDao.findAll();
        ArrayList<Post> formatedPosts = new ArrayList<>();
        for(Post post : posts) {
            Post newPost = new Post(post);
            if(newPost.getBody().length() >= 100){
                newPost.setBody(newPost.getBody().substring(0,100) +"...");
            }
            formatedPosts.add(newPost);
        }
        Iterable<Post> formatedResults = formatedPosts;
        model.addAttribute("post", formatedResults);
        return "Posts/index";
    }

    @GetMapping("/posts/search")
    public String buildSearch(
            @RequestParam(name = "query") String query,
            Model model){
        Iterable<Post> posts = postDao.findAll();
        ArrayList<Post> search = new ArrayList<>();
        query = query.toLowerCase();
        for(Post post : posts){
            if(post.getTitle().toLowerCase().contains(query) ||
               post.getBody().toLowerCase().contains(query) ||
               post.getUser().getUsername().toLowerCase().contains(query)||
               post.getUser().getEmail().toLowerCase().contains(query)){
                    search.add(post);
            }
        }
        Iterable<Post> searchResults = search;
        model.addAttribute("post", searchResults);
        return "Posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable long id, Model model){
        Post post = postDao.findOne(id);
        Iterable<Photo> photos = photoDao.findByPost_Id(id);
        model.addAttribute("post", post);
        model.addAttribute("photos", photos);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String getEditForm(@PathVariable long id, Model model) {
        Post post = postDao.findOne(id);
        model.addAttribute("post",post);
        return "Posts/edit";
    }

    @PostMapping("/posts/edit")
    public String edit(@RequestParam(name = "idEdit") long id,
                       @RequestParam(name = "titleEdit") String title,
                       @RequestParam(name = "bodyEdit") String body) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.findOne(id);
        post.setUser(user);
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
        return "redirect:/profile";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.findOne(id);
        if(user.getId() != post.getUser().getId()){
            return "redirect: /posts/{id}";
        }
        postDao.delete(post);
        return("redirect:/profile");
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model){
        model.addAttribute("post", new Post());
        return "Posts/create";
    }

    @PostMapping("/posts/create")
    public String postPost(@ModelAttribute Post post, @RequestParam(name = "file") String photoArr){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postDao.save(post);
        String[] photos = photoArr.split("-");
        for(String photoURL : photos){
            Photo photo = new Photo(photoURL,post);
            photoDao.save(photo);
        }

//        emailService.prepareAndSend(
//                post,
//                "Post created",
//                "Post with the id "+post.getId()+" has been created");
        return "redirect:/posts/"+post.getId();
    }
}
