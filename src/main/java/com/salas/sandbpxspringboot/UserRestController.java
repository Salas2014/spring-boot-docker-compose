package com.salas.sandbpxspringboot;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserRestController implements RowMapper<UserRestController.User> {

    private final NamedParameterJdbcOperations jdbcOperations;

    public UserRestController(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("c_name"));
    }

    @GetMapping
    public List<User> getAll() {
        return jdbcOperations.query("select * from t_user", this);
    }

    record User(int id, String name) {
    }
}
