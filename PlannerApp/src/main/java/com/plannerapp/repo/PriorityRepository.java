package com.plannerapp.repo;

import com.plannerapp.model.Enums.PriorityName;
import com.plannerapp.model.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {


    Priority findByPriorityName(PriorityName priorityName);

}
