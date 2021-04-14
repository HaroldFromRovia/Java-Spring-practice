package ru.itis.kpfu.bentos.springboothomework.repository.myBatis;

import lombok.extern.java.Log;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.bentos.springboothomework.aop.annotations.LogMethod;
import ru.itis.kpfu.bentos.springboothomework.models.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @LogMethod
    @Select("SELECT * from users where id=#{id}")
    User findById(@Param("id") Long id);

    @LogMethod
    @Select("SELECT * from users")
    List<User> findAll();

    @LogMethod
    @Insert("INSERT into users (birthday, country, email, gender, name, password_hash) VALUES (#{id}, #{birthday}, #{country}, #{email}, #{gender}, #{name}, #{password_hash})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    User save(User user);
}
