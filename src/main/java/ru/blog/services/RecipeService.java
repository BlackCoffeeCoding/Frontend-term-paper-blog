package ru.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.blog.dto.RecipeDto;
import ru.blog.models.entities.Recipe;
import ru.blog.repositories.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository repository;
    private final ModelMapper mapper;

    public RecipeService(RecipeRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<RecipeDto> getAll() {
        return repository.findByArchivedFalse()
                .stream()
                .map(recipe -> mapper.map(recipe, RecipeDto.class))
                .toList();
    }

    public List<RecipeDto> getArchived() {
        return repository.findAllByArchivedTrue()
                .stream()
                .map(recipe -> mapper.map(recipe, RecipeDto.class))
                .toList();
    }


    public RecipeDto save(RecipeDto dto) {
        Recipe recipe = mapper.map(dto, Recipe.class);
        Recipe saved = repository.saveAndFlush(recipe);
        return mapper.map(saved, RecipeDto.class);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public void archive(String id) {
        Recipe recipe = repository.findById(id).orElseThrow();
        recipe.setArchived(true);
        repository.saveAndFlush(recipe);
    }
}
