package am.itspace.authorbookrest.dto;

import am.itspace.authorbookrest.entity.Gender;
import am.itspace.authorbookrest.entity.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    private String name;

    private String surname;

    private String email;

    private UserType userType;
}
