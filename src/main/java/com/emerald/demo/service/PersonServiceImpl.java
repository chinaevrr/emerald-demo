package com.emerald.demo.service;

import com.emerald.demo.model.Person;
import com.emerald.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public Person createPerson(Person person) {
        log.debug("Create person invoked: {}", person);

        Person createdPerson = personRepository.create(person);

        log.info("Person created successfully. Details: {}", createdPerson.getId());
        return createdPerson;
    }
}
