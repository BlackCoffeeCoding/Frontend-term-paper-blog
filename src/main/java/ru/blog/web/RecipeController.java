package ru.blog.web;

import org.springframework.web.bind.annotation.*;
import ru.blog.dto.RecipeDto;
import ru.blog.services.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "*")
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    public List<RecipeDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/archived")
    public List<RecipeDto> getArchived() {
        return service.getArchived();
    }


    @PostMapping
    public RecipeDto create(@RequestBody RecipeDto dto) {
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PutMapping("/{id}/archive")
    public void archive(@PathVariable String id) {
        service.archive(id);
    }
}
