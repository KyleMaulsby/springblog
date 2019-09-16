package com.codeup.springblog.controller;

import com.codeup.springblog.model.Post;
import com.codeup.springblog.repo.PostRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class AjaxController {

    private PostRepo postDao;

    public AjaxController(PostRepo postDao){
        this.postDao = postDao;
    }

    @GetMapping("/posts.json")
    public @ResponseBody Iterable<Post> viewAllPostsInAjax() {
        System.out.println("here we are in veiw all posts in ajax");
        return postDao.findAll();
    }
}
