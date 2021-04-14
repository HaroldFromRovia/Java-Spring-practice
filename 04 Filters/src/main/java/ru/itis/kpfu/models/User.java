package ru.itis.kpfu.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
public class User {

    @NonNull
    private String name;
    private Long id;
    @NonNull
    private String passwordHash;
    @NonNull
    private String email;
    private boolean isActivated;
    private String registrationToken;


}
