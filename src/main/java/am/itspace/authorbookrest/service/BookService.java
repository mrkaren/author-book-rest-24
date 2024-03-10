package am.itspace.authorbookrest.service;

import am.itspace.authorbookrest.dto.BookDto;
import am.itspace.authorbookrest.dto.SaveBookDto;

public interface BookService {
    BookDto save(SaveBookDto saveBookDto);
}
