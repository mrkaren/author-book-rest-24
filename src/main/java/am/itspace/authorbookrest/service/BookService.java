package am.itspace.authorbookrest.service;

import am.itspace.authorbookrest.dto.BookDto;
import am.itspace.authorbookrest.dto.BookFilterDto;
import am.itspace.authorbookrest.dto.SaveBookDto;

import java.util.List;

public interface BookService {
    BookDto save(SaveBookDto saveBookDto);

    List<BookDto> getAll();

    List<BookDto> getAllByFilter(BookFilterDto bookFilterDto);
}
