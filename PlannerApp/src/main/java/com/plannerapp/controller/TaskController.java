package com.plannerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("task")
public class TaskController {

    @GetMapping("/add")
    public ModelAndView addTask(){
        return new ModelAndView("task-add");
    }
}
