package ru.netology.dto;

import org.mapstruct.Mapper;
import ru.netology.dto.PostDto;
import ru.netology.model.Post;

@Mapper(componentModel = "spring")
public interface PostPostDtoMapper {
    PostDto postToPostDto(Post post);

    Post postDtoToPost(PostDto postDto);
}
