package com.plannerapp.service.Impl;

import com.plannerapp.model.TaskAddBindingModel;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void addTask(TaskAddBindingModel taskAddBindingModel) {

        Priority priority = priorityRepository.findByPriorityName(taskAddBindingModel.getPriority());

        if(priority != null){

            Task task = new Task();
            task.setDescription(taskAddBindingModel.getDescription());
            task.setDueDate(LocalDate.parse(taskAddBindingModel.getDueDate()));
            task.setPriority(priority);

            taskRepository.save(task);
        }

    }
}
