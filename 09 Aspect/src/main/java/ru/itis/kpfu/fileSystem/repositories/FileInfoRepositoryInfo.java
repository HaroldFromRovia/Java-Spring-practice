package ru.itis.kpfu.fileSystem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.fileSystem.models.FileInfo;
import ru.itis.kpfu.fileSystem.models.User;
import ru.itis.kpfu.fileSystem.repositories.interfaces.FileInfoRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class FileInfoRepositoryInfo implements FileInfoRepository {

    private final String SQL_INSERT_FILE_INFO = "insert into " +
            "spring.file_info (storage_file_name, original_file_name, size, type, url) values (?,?,?,?,?);";
    private final String SQL_SELECT_BY_ID = "select * from spring.file_info where id = ?;";
    private final String SQL_SELECT_ALL = "select * from spring.file_info";
    private final String SQL_DELETE_FILE_INFO = "delete from spring.file_info where id=?";
    private final String SQL_SELECT_BY_NAME = "select * from spring.file_info where storage_file_name = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(FileInfo model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT_FILE_INFO);
            statement.setString(1, model.getStorageFileName());
            statement.setString(2, model.getOriginalFileName());
            statement.setLong(3, model.getSize());
            statement.setString(4, model.getType());
            statement.setString(5, model.getUrl());
            return statement;
        }, keyHolder);
    }

    @Override
    public Optional<FileInfo> findById(Long id) {
        FileInfo course = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, fileInfoRowMapper);
        return Optional.ofNullable(course);
    }

    @Override
    public Optional<FileInfo> findFileInfoByName(String name) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, new Object[]{name}, fileInfoRowMapper);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_FILE_INFO, new Object[]{id}, fileInfoRowMapper);
    }

    @Override
    public List<FileInfo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, fileInfoRowMapper);
    }

    private RowMapper<FileInfo> fileInfoRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("original_file_name"))
                    .storageFileName(row.getString("storage_file_name"))
                    .type(row.getString("type"))
                    .url(row.getString("url"))
                    .size(row.getLong("size"))
                    .build();

}
