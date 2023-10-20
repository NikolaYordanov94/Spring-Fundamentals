package com.plannerapp.init;

import com.plannerapp.model.Enums.PriorityName;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataBaseInit implements CommandLineRunner {
    private final PriorityRepository priorityRepository;

    public DataBaseInit(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        boolean hasPriorities = priorityRepository.count() > 0;

        if(!hasPriorities){
            List<Priority> priorities = new ArrayList<>();

            Arrays.stream(PriorityName.values())
                    .forEach(priorityName -> {
                        Priority priority = new Priority();
                        priority.setPriorityName(priorityName);
                        priorities.add(priority);
                    });

            priorityRepository.saveAll(priorities);
        }

    }
}
