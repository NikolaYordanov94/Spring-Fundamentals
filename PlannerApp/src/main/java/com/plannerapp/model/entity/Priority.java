package com.plannerapp.model.entity;

import com.plannerapp.model.Enums.PriorityName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private PriorityName priorityName;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<Task> tasks;

    public Priority() {
    }

    public PriorityName getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(PriorityName priorityName) {
        this.priorityName = priorityName;
        setDescription(priorityName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(PriorityName priorityName) {
        String description = "";

        switch (priorityName){
            case URGENT:
                description = "An urgent problem that blocks the system use until the issue is resolved.";
                break;
            case IMPORTANT:
                description = "A core functionality that your product is explicitly supposed to perform is compromised.";
                break;
            case LOW:
                description = "Should be fixed if time permits but can be postponed.";
                break;
        }
        this.description = description;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
