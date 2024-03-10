package am.itspace.authorbookrest.service.impl;

import am.itspace.authorbookrest.dto.CreateUserRequestDto;
import am.itspace.authorbookrest.dto.UserDto;
import am.itspace.authorbookrest.entity.User;
import am.itspace.authorbookrest.mapper.UserMapper;
import am.itspace.authorbookrest.repository.UserRepository;
import am.itspace.authorbookrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(CreateUserRequestDto createUserRequestDto) {
        User user = userMapper.map(createUserRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.map(userRepository.save(user));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }
}
