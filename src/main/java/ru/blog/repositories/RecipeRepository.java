package ru.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.blog.models.entities.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String>{
    List<Recipe> findByArchivedFalse();
    List<Recipe> findAllByArchivedTrue();
}
