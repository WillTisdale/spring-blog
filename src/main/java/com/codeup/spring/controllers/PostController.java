package com.codeup.spring.controllers;

import com.codeup.spring.models.Post;
import com.codeup.spring.models.User;
import com.codeup.spring.repositories.PostRepository;
import com.codeup.spring.repositories.UserRepository;
import com.codeup.spring.services.EmailService;
import com.codeup.spring.services.UserService;
import com.codeup.spring.util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public PostController(PostRepository postsDao, UserRepository usersDao, EmailService emailService, UserService userService){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/posts")
    public String all(Model model, @PageableDefault(value=4) Pageable pageable){
        model.addAttribute("page", postsDao.findAll(pageable));
        model.addAttribute("title", "all posts");
        return "spring-blog/index";
    }

    @GetMapping("/posts/{id}")
    public String post(Model model, @PathVariable long id){
        Post post = postsDao.getOne(id);
        User user = userService.loggedInUser();
        boolean isUsers = user.getId() == post.getUser().getId();
        model.addAttribute("isUsers", isUsers);
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
    public String createPost(@ModelAttribute @Validated Post post, Errors validation, Model model){
        for (Post p : postsDao.findAll()){
            System.out.println(p.getTitle());
            if(p.getTitle().equalsIgnoreCase(post.getTitle()) && p.getUser().getId() == post.getUser().getId()){
                validation.rejectValue(
                        "title",
                        "post.title",
                        "you already have a post with the title " + post.getTitle());
            }
        }
        if (post.getTitle().isEmpty()) {
            validation.rejectValue(
                    "title",
                    "post.title",
                    "Title can not be empty"
            );
        }
        if (post.getBody().isEmpty()){
            validation.rejectValue(
                    "body",
                    "post.body",
                    "Body can not be empty"
            );
        }
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "spring-blog/posts/create";
        }
        User user = userService.loggedInUser();
        post.setUser(user);
        post.setDate(new Date());
        System.out.println(post.getDate());

        Post savedPost = postsDao.save(post);

        String subject = "New Post Created Successfully!";
        String body = "Dear " + savedPost.getUser().getUsername() + "," +
                "\n\nThank you for creating a post.\n" +
                savedPost.getBody();

        emailService.prepareAndSend(savedPost, subject, body);
        return "redirect:/posts/" + savedPost.getId();
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
    private String editPost(@ModelAttribute @Validated Post post, Errors validation, Model model){
//        for (Post p : postsDao.findAll()){
//            System.out.println(p.getTitle());
//            if(p.getTitle().equalsIgnoreCase(post.getTitle()) &&
//                    p.getUser().getId() == post.getUser().getId()){
//                validation.rejectValue(
//                        "title",
//                        "post.title",
//                        "you already have a post with the title " + post.getTitle());
//            }
//        }
//        if (post.getTitle().isBlank()) {
//            validation.rejectValue(
//                    "title",
//                    "post.title",
//                    "Title can not be empty"
//            );
//        }
//        if (post.getBody().isBlank()){
//            validation.rejectValue(
//                    "body",
//                    "post.body",
//                    "Body can not be empty"
//            );
//        }
//        if(validation.hasErrors()){
//            model.addAttribute("errors", validation);
//            model.addAttribute("post", post);
//            model.addAttribute("title", "edit post");
//            return "spring-blog/posts/edit";
//        }
        User user = userService.loggedInUser();
        post.setUser(user);
        post.setDate(new Date());
        postsDao.save(post);
        return "redirect:/posts/" + post.getId();
    }
}
