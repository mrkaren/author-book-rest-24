package am.itspace.authorbookrest.endpoint;

import am.itspace.authorbookrest.dto.*;
import am.itspace.authorbookrest.entity.User;
import am.itspace.authorbookrest.mapper.UserMapper;
import am.itspace.authorbookrest.service.UserService;
import am.itspace.authorbookrest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserEndpoint {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;

    @Value("${upload.image.path}")
    private String uploadImagePath;

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

    @PostMapping("/image/{id}")
    public ResponseEntity<UserDto> uploadImage(@PathVariable("id") int userId,
                                               @RequestParam("picture") MultipartFile multipartFile) throws IOException {

        User byId = userService.findById(userId);
        if (byId == null) {
            return ResponseEntity.notFound().build();
        }
        userService.uploadImage(byId, multipartFile);
        return ResponseEntity.ok(userMapper.map(byId));
    }


    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        File file = new File(uploadImagePath, picName);
        if (file.exists()) {
            return IOUtils.toByteArray(new FileInputStream(file));
        }
        return null;
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
