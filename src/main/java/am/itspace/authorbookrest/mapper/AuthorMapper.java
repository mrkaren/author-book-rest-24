package am.itspace.authorbookrest.mapper;

import am.itspace.authorbookrest.dto.AuthorResponseDto;
import am.itspace.authorbookrest.dto.SaveAuthorDto;
import am.itspace.authorbookrest.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public interface AuthorMapper {


    AuthorResponseDto map(Author author);

    @Mapping(target = "createdDate", expression = "java(LocalDate.now())")
    Author map(SaveAuthorDto saveAuthorDto);

}
