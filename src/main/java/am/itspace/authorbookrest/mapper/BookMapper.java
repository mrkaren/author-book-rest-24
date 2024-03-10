package am.itspace.authorbookrest.mapper;

import am.itspace.authorbookrest.dto.BookDto;
import am.itspace.authorbookrest.dto.SaveBookDto;
import am.itspace.authorbookrest.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface BookMapper {

    @Mapping(target = "authorResponseDto", source = "author")
    BookDto map(Book book);

    Book map(SaveBookDto saveBookDto);

}
