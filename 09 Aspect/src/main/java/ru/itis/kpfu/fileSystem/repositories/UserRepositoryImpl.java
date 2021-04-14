package ru.itis.kpfu.fileSystem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.fileSystem.models.User;
import ru.itis.kpfu.fileSystem.repositories.interfaces.UserRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final String SQL_INSERT_USER = "insert into " +
            "spring.user (name, password_hash, email,role,state) values (?,?,?,?,?);";
    private final String SQL_SELECT_BY_ID = "select * from spring.user where user_id = ?;";
    private final String SQL_SELECT_ALL = "select * from spring.user";
    private final String SQL_DELETE_USER = "delete from spring.user where user_id=?";
    private final String SQL_SELECT_BY_NAME = "select * from spring.user where name = ?;";
    private final String SQL_SELECT_BY_EMAIL = "select * from spring.user where email = ?;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT_USER);
            statement.setString(1, model.getName());
            statement.setString(2, model.getPasswordHash());
            statement.setString(3, model.getEmail());
            statement.setString(4, model.getRole().toString());
            statement.setString(5, model.getState().toString());
            return statement;
        }, keyHolder);
    }

    @Override
    public Optional<User> findById(Long id) {
        User course = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, userRowMapper);
        return Optional.ofNullable(course);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER, new Object[]{id}, userRowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("user_id"))
                    .email(row.getString("email"))
                    .name(row.getString("name"))
                    .passwordHash(row.getString("password_hash"))
                    .role(User.Role.valueOf(row.getString("role")))
                    .state(User.State.valueOf(row.getString("state")))
                    .build();

    @Override
    public Optional<User> findUserByName(String name) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, new Object[]{name}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
