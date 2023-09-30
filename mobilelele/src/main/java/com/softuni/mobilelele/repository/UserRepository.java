package com.softuni.mobilelele.repository;

import com.softuni.mobilelele.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
