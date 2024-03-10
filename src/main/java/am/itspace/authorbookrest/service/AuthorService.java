package am.itspace.authorbookrest.service;

import am.itspace.authorbookrest.dto.AuthorResponseDto;
import am.itspace.authorbookrest.dto.SaveAuthorDto;
import am.itspace.authorbookrest.entity.Author;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto create(SaveAuthorDto author);

    List<AuthorResponseDto> getAll();

    AuthorResponseDto getById(int id);

    AuthorResponseDto update(int id, SaveAuthorDto author);

    void deleteById(int id);
}
