package ru.itis.kpfu.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInForm {

    private String name;
    private String password;

}
