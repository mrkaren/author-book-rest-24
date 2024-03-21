package am.itspace.authorbookrest.endpoint;

import am.itspace.authorbookrest.dto.BookDto;
import am.itspace.authorbookrest.dto.BookFilterDto;
import am.itspace.authorbookrest.dto.SaveBookDto;
import am.itspace.authorbookrest.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/books")
@RequiredArgsConstructor
public class BookEndpoint {

    private final BookService bookService;

    @PostMapping()
    public BookDto create(@Valid @RequestBody SaveBookDto saveBookDto) {
        return bookService.save(saveBookDto);
    }

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @PostMapping("/filter")
    public List<BookDto> getAllByFilter(@RequestBody BookFilterDto bookFilterDto) {
        return bookService.getAllByFilter(bookFilterDto);
    }

}
