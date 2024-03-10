package am.itspace.authorbookrest.service;

import am.itspace.authorbookrest.dto.CreateUserRequestDto;
import am.itspace.authorbookrest.dto.UserDto;
import am.itspace.authorbookrest.entity.User;


public interface UserService {

    UserDto create(CreateUserRequestDto createUserRequestDto);

    User findByEmail(String email);

}
