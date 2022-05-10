package com.emerald.demo.service;

import com.emerald.demo.model.Person;
import com.emerald.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    public static final UUID ID = UUID.randomUUID();
    public static final String NAME = "name";
    public static final int AGE = 16;
    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        when(personRepository.create(any()))
                .thenReturn(new Person(ID, NAME, AGE));
    }

    @Test
    void shouldDeleteToRepository() {
        Person person = personService.createPerson(new Person());
        assertEquals(ID, person.getId());
    }
}
