package am.itspace.authorbookrest.service.impl;

import am.itspace.authorbookrest.dto.BookDto;
import am.itspace.authorbookrest.dto.SaveBookDto;
import am.itspace.authorbookrest.entity.Book;
import am.itspace.authorbookrest.mapper.BookMapper;
import am.itspace.authorbookrest.repository.AuthorRepository;
import am.itspace.authorbookrest.repository.BookRepository;
import am.itspace.authorbookrest.service.AuthorService;
import am.itspace.authorbookrest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(SaveBookDto saveBookDto) {
        Book book = bookMapper.map(saveBookDto);
        book.setAuthor(authorRepository.findById(saveBookDto.getAuthorId()).orElse(null));
        bookRepository.save(book);
        return bookMapper.map(book);
    }

}
