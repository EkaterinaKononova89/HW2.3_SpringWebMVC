package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.dto.PostDto;
import ru.netology.dto.PostPostDtoMapper;
import ru.netology.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repository; // завязан на интерфейс, а не конкретную реализацию
    private final PostPostDtoMapper mapper;

    public PostService(PostRepository repository, PostPostDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PostDto> all() {
        return repository.all()
                .stream()
                .filter(x -> !x.getRemoved())
                .map(mapper::postToPostDto)
                .collect(Collectors.toList());
    }

    public PostDto getById(long id) {
        return repository.getById(id)
                .filter(x -> !x.getRemoved())
                .map(mapper::postToPostDto)
                .orElseThrow(NotFoundException::new);
    }

    public PostDto save(Post post) { // какой тип лучше указать в параметрах? Post или DTO?
        //Post post = mapper.postDtoToPost(postDto);
        if (repository.save(post) == null) {
            throw new NotFoundException("Not found post with ID " + post.getId());
        }
        return mapper.postToPostDto(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}
