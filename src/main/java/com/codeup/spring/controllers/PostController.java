package com.codeup.spring.controllers;

import com.codeup.spring.models.Post;
import com.codeup.spring.models.User;
import com.codeup.spring.repositories.PostRepository;
import com.codeup.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;

    @Autowired
    public PostController(PostRepository postsDao, UserRepository usersDao){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String all(Model model, @PageableDefault(value=4) Pageable pageable){
        model.addAttribute("page", postsDao.findAll(pageable));
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
        String search;
        Post post = new Post(title, body, usersDao.getOne(1L));
        postsDao.save(post);
        String id = String.valueOf(post.getId());
        return "posts/index";
    }

    @GetMapping(path = "/posts/delete/{id}")
    public String delete(Model model, @PathVariable long id){
        postsDao.deleteById(id);
        return "posts/index";
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
        Post post = new Post(id, title, body, usersDao.getOne(1L));
        postsDao.save(post);
        return post(model, id);
    }
}
