package ru.itis.kpfu.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.itis.kpfu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.repositories.interfaces.UserRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final String SQL_INSERT_USER = "insert into " +
            "spring.user (name, password_hash, email, state, reg_token) values (?,?,?,?,?);";
    private final String SQL_SELECT_BY_ID = "select * from spring.user where user_id = ?;";
    private final String SQL_SELECT_ALL = "select * from spring.user";
    private final String SQL_DELETE_USER = "delete from spring.user where user_id=?";
    private final String SQL_SELECT_BY_NAME = "select * from spring.user where name = ?;";
    private final String SQL_SELECT_BY_EMAIL = "select * from spring.user where email = ?;";
    private final String SQL_SELECT_BY_REG_TOKEN = "select * from spring.user where reg_token = ?;";
    private final String SQL_UPDATE_PRODUCT = "update spring.user set state=?, reg_token='' where reg_token=?";

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
            statement.setBoolean(4, model.isActivated());
            statement.setString(5, model.getRegistrationToken());
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
                    .isActivated(row.getBoolean("is_confirmed"))
                    .registrationToken(row.getString("reg_token"))
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

    @Override
    public Optional<User> findUserByRegToken(String token) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_REG_TOKEN, new Object[]{token}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(User model) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_UPDATE_PRODUCT);
            statement.setBoolean(1, true);
            statement.setString(2, model.getRegistrationToken());
            return statement;
        });
    }
}
