package ru.itis.kpfu.fileSystem.models;

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
    private Role role;
    private State state;

    public enum State{
        CONFIRMED,
        NON_CONFIRMED
    }

    public enum Role{
        USER,
        ADMIN
    }


}
