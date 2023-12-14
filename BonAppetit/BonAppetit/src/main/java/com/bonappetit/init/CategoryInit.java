package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CategoryInit implements CommandLineRunner {

    private final CategoryRepository categoryRepository;


    public CategoryInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Long count = categoryRepository.count();

        if(count == 0){
            List<Category> categories = new ArrayList<>();

            Arrays.stream(CategoryName.values())
                    .forEach(CategoryName -> {
                        Category category = new Category();
                        category.setCategoryName(CategoryName);
                        categories.add(category);
                    });

            categoryRepository.saveAll(categories);
        }
    }
}
