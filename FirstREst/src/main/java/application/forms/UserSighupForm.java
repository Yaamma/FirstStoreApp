package application.forms;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserSighupForm {
    @Size(min=4,max=15)
    private String name;
    @Size(min=8,max=50)
    private String password;
    @Size(min=8,max=50)
    private String lastname;
    @Size(min=5,max=50)
    @Email
    private String mail;
    @Max(99)
    @Min(8)
    private int age;
    @Size(max=15,min=5)
    private String login;
}
