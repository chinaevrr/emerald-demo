package com.emerald.demo.repository;

import com.emerald.demo.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {
    private static final String INSERT_SQL_QUERY = "insert into person_entity(id, name, age) values (?, ?, ?)";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Person create(Person person) {
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(INSERT_SQL_QUERY, id, person.getName(), person.getAge());
        return new Person(id, person.getName(), person.getAge());
    }
}
