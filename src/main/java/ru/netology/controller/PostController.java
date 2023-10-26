package ru.netology.controller;


import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.dto.PostDto;
import ru.netology.service.PostService;

import java.util.List;

@RestController // замена с @Controller позволяет делать автоматич конвертацию JSON
@RequestMapping("api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<PostDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public PostDto save(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }
}