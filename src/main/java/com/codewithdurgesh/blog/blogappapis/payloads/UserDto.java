package com.codewithdurgesh.blog.blogappapis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 characters")
    private String name;

    @NotEmpty
    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 and max of 10 characters")
    private String password;

    @NotEmpty
    private String about;
}
