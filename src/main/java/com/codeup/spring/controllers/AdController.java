package com.codeup.spring.controllers;

import com.codeup.spring.models.Ad;
import com.codeup.spring.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdController {

    private final AdRepository adsDao;

    public AdController(AdRepository adsDao){
        this.adsDao = adsDao;
    }

    @GetMapping("/ads/jps")
    @ResponseBody
    public List<Ad> jpaIndex(){
        return adsDao.findAll();
    }

    @GetMapping("/ads/jpa/{id}")
    public String viewJpaAd(@PathVariable long id, Model model){
        model.addAttribute("ad", adsDao.getOne(id));
        return "showAd";
    }
}
