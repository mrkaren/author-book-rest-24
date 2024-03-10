package am.itspace.authorbookrest.dto;

import am.itspace.authorbookrest.entity.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveAuthorDto {

    @JsonProperty("name")
    private String name;

    private String surname;

    private Gender gender;

    private int age;

}
