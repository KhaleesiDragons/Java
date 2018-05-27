package com.springboot.controller;

import com.springboot.model.Person;
import com.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MyController {
    private String message = "Fill the form";

    @GetMapping("/")
    public String myForm(Model model) {
        model.addAttribute("person", new Person());
        return "index";
    }

    @Autowired
    PersonRepository repository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String table(Model model) {
        model.addAttribute("list", repository.findAll());
        return "list";
    }

    @PostMapping("/index")
    public void personSubmit(@ModelAttribute Person person, Map<String, Object> model) {
        repository.addPerson(person);
        model.put("message", "Data have saved");
    }


}



