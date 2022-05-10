package com.emerald.demo.component;

import com.emerald.demo.dto.PersonDto;
import com.emerald.demo.model.Person;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonConverterTest {
    public static final UUID ID = UUID.randomUUID();
    public static final String NAME = "name";
    public static final int AGE = 15;
    private final PersonConverter personConverter = new PersonConverter();

    @Test
    void shouldConvertDtoToPerson() {
        Person person = personConverter.convert(new PersonDto(ID, NAME, AGE));
        assertNotNull(person);
        assertEquals(ID, person.getId());
        assertEquals(NAME, person.getName());
        assertEquals(AGE, person.getAge());
    }
}
