package com.emerald.demo;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.emerald.demo.dto.PersonDto;
import com.emerald.demo.model.Person;
import com.emerald.demo.service.PersonAwsS3Uploader;
import com.emerald.demo.service.PersonService;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.messaging.Message;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonFunctionTest {
    public static final String NAME = "name";
    public static final int AGE = 16;
    @Mock
    private PersonService personService;
    @Mock
    private Validator validator;
    @Mock
    private Converter<PersonDto, Person> personConverter;
    @Mock
    private PersonAwsS3Uploader personAwsS3Uploader;
    @InjectMocks
    private PersonFunction personFunction;
    @Mock
    private Message<PersonDto> message;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldReturnCreatedStatus() {
        when(personService.createPerson(any())).thenReturn(new Person(UUID.randomUUID(), NAME, AGE));
        when(message.getPayload()).thenReturn(new PersonDto(null, NAME, AGE));

        APIGatewayProxyResponseEvent responseEvent = personFunction.apply(message);
        assertEquals(HttpStatus.SC_CREATED, responseEvent.getStatusCode());
    }

    @Test
    void shouldReturnBadRequest() {
        when(validator.validate(any())).thenReturn(Set.of(mock(ConstraintViolation.class)));
        when(message.getPayload()).thenReturn(new PersonDto());

        personFunction.apply(message);
    }
}
