package com.codeup.spring.controllers;

import com.codeup.spring.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String all(Model model){
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post(1, "First Post", "This is my first post");
        Post post2 = new Post(2, "Second Post", "This is my 2nd post");
        Post post3 = new Post(3, "Third Post", "This is my 3rd post");
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        model.addAttribute("posts", posts);
        model.addAttribute("title", "All Posts");
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String post(Model model, @PathVariable long id){
        Post post = new Post(1, "Selected Post", "You selected this post!");
        model.addAttribute("post", post);
        model.addAttribute("title", "Single Post");
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createForm(){
        return "view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPost(){
        return "create a new post";
    }

}
