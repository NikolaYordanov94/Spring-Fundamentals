package com.plannerapp.controller;

import com.plannerapp.model.TaskAddBindingModel;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/add")
    public ModelAndView addTask(@ModelAttribute("taskAddBindingModel") TaskAddBindingModel taskAddBindingModel){
        return new ModelAndView("task-add");
    }

    @PostMapping("/add")
    public ModelAndView addTask(@ModelAttribute("taskAddBindingModel") @Valid TaskAddBindingModel taskAddBindingModel,
                                BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ModelAndView("task-add");
        }

        taskService.addTask(taskAddBindingModel);

        return new ModelAndView("redirect:/home");
    }
}
