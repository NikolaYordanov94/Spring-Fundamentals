package com.bonappetit.repo;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByCategory_CategoryName(CategoryName categoryName);
}
