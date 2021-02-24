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

    @GetMapping("/posts")
    public String all(Model model, @PageableDefault(value=4) Pageable pageable){
        model.addAttribute("page", postsDao.findAll(pageable));
        model.addAttribute("title", "all posts");
        return "spring-blog/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String post(Model model, @PathVariable long id){
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        model.addAttribute("title", post.getTitle());
        return "spring-blog/posts/show";
    }

    @GetMapping("/posts/create")
    public String createForm(Model model){
        model.addAttribute("post", new Post());
        model.addAttribute("title", "create post");
        return "spring-blog/posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        post.setUser(usersDao.getOne(1L));
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping(path = "/posts/delete/{id}")
    public String delete(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping(path = "/posts/edit/{id}")
    private String editForm(Model model, @PathVariable long id){
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        model.addAttribute("title", "edit post");
        return "spring-blog/posts/edit";
    }

    @PostMapping(path = "/posts/edit/{id}")
    private String editPost(@ModelAttribute Post post){
        post.setUser(usersDao.getOne(1L));
        postsDao.save(post);
        return "redirect:/posts/" + post.getId();
    }
}
