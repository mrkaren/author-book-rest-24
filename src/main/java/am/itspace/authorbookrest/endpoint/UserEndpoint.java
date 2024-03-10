package am.itspace.authorbookrest.endpoint;

import am.itspace.authorbookrest.dto.*;
import am.itspace.authorbookrest.entity.User;
import am.itspace.authorbookrest.mapper.UserMapper;
import am.itspace.authorbookrest.service.UserService;
import am.itspace.authorbookrest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserEndpoint {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequestDto createUserRequestDto) {
        User byEmail = userService.findByEmail(createUserRequestDto.getEmail());
        if (byEmail != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        return ResponseEntity.ok(userService.create(createUserRequestDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        User user = userService.findByEmail(authRequestDto.getEmail());
        if (user == null
                || !passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
        return ResponseEntity.ok(AuthResponseDto.builder()
                        .token(jwtTokenUtil.generateToken(user.getEmail()))
                        .userDto(userMapper.map(user))
                .build());


    }

}
