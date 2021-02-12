package com.codeup.spring.controllers;

import com.codeup.spring.models.Post;
import com.codeup.spring.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;

    public PostController(PostRepository postsDao){
        this.postsDao = postsDao;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String all(Model model){
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("title", "all posts");
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String post(Model model, @PathVariable long id){
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        model.addAttribute("title", post.getTitle());
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String createForm(Model model){
        model.addAttribute("title", "create post");
        return "posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String createPost(Model model, @RequestParam("title") String title, @RequestParam("body") String body){
        Post post = new Post(title, body);
        postsDao.save(post);
        String id = String.valueOf(post.getId());
        return all(model);
    }

    @GetMapping(path = "/posts/delete/{id}")
    public String delete(Model model, @PathVariable long id){
        postsDao.deleteById(id);
        return all(model);
    }

    @GetMapping(path = "/posts/edit/{id}")
    private String editForm(Model model, @PathVariable long id){
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        model.addAttribute("title", "edit post");
        return "posts/edit";
    }

    @PostMapping(path = "/posts/edit/{id}")
    private String editPost(Model model, @RequestParam("title") String title, @RequestParam("body") String body, @RequestParam("id") long id){
        Post post = new Post(id, title, body);
        postsDao.save(post);
        return post(model, id);
    }
}
