package io.github.maksymdolgykh.dropwizard.example.dao;


import io.github.maksymdolgykh.dropwizard.example.model.Person;
import io.github.maksymdolgykh.dropwizard.example.dao.mapper.PersonMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(PersonMapper.class)
public interface PersonDao {

    @SqlQuery("SELECT * FROM people")
    List<Person> getAllPeople();

    @SqlQuery("SELECT * FROM people WHERE id = :id")
    Person findPersonById(@Bind("id") long id);

    @SqlUpdate("DELETE FROM people WHERE id = :id")
    int deletePersonById(@Bind("id") long id);

    @SqlUpdate("UPDATE people SET full_name = :fullName, job_title = :jobTitle, year_born = :yearBorn WHERE id = :id")
    int updatePerson(@BindBean Person person);

    @SqlUpdate("INSERT INTO people (full_name, job_title, year_born) values (:fullName, :jobTitle, :yearBorn)")
    @GetGeneratedKeys("id")
    int createPerson(@BindBean Person person);
}