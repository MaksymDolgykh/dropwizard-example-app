package io.github.maksymdolgykh.dropwizard.example.dao.mapper;

import io.github.maksymdolgykh.dropwizard.example.model.Person;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.core.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person>
{
    public Person map(ResultSet rs, StatementContext statementContext) throws SQLException
    {
        return new Person(rs.getLong("id"), rs.getString("full_name"),
                rs.getString("job_title"), rs.getInt("year_born"));
    }
}
