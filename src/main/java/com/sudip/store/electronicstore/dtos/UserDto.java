package com.sudip.store.electronicstore.dtos;

import com.sudip.store.electronicstore.validate.ImageNameValidate;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String id;

    @Size(min = 3, max = 20, message = "Name should be between 3 and 20 Characters")
    private String name;

   // @Pattern(regexp = "\\A[A-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[A-Z0-9.-]+\\Z\n",message = "Invalid email format")
    @Email(message ="Invalid email format" )
    @NotBlank(message = "Email field cannot be blank")
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @Size(min = 4, max = 6, message = "gender should be 4 to 6 characters")
    private String gender;

    @NotBlank(message = "Write something about yourself")
    private String about;

    @ImageNameValidate
    private String imageName;
}
