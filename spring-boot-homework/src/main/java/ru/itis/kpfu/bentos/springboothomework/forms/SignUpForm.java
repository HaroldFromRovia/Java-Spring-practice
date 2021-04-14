package ru.itis.kpfu.bentos.springboothomework.forms;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Resource;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Resource
public class SignUpForm {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank(message = "Email is mandatory, and should be valid")
    @Email
//    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
//            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 30)
    private String password;
    @NotBlank(message = "Password confirmation is mandatory")
    @Size(min = 6, max = 30)
    private String passwordConfirm;
    private String country;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @NotBlank
    private String gender;
}
