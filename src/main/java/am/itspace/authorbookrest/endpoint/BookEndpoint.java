package am.itspace.authorbookrest.endpoint;

import am.itspace.authorbookrest.dto.BookDto;
import am.itspace.authorbookrest.dto.SaveBookDto;
import am.itspace.authorbookrest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/books")
@RequiredArgsConstructor
public class BookEndpoint {

    private final BookService bookService;

    @PostMapping()
    public BookDto create(@RequestBody SaveBookDto saveBookDto) {
        return bookService.save(saveBookDto);
    }

}
