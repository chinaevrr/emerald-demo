package com.emerald.demo.component;

import com.emerald.demo.dto.PersonDto;
import com.emerald.demo.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter implements Converter<PersonDto, Person> {

    @Override
    public Person convert(PersonDto source) {
        return new Person(source.getId(), source.getName(), source.getAge());
    }
}
