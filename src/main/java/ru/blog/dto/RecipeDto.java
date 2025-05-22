package ru.blog.dto;

import jakarta.validation.constraints.NotEmpty;

public class RecipeDto {
    private String id;
    private String title;
    private String category;
    private String author;
    private String content;
    private boolean archived;

    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty(message = "Title must not be null or empty!")
    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @NotEmpty(message = "Category must not be null or empty!")
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @NotEmpty(message = "Author must not be null or empty!")
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    @NotEmpty(message = "Content must not be null or empty!")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @NotEmpty(message = "Must be archived or not archived!")
    public boolean isArchived() {
        return archived;
    }
    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
