package com.emerald.demo;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.emerald.demo.dto.PersonDto;
import com.emerald.demo.model.Person;
import com.emerald.demo.service.PersonAwsS3Uploader;
import com.emerald.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

@Component
@Slf4j
@RequiredArgsConstructor
public class PersonFunction implements Function<Message<PersonDto>, APIGatewayProxyResponseEvent> {
    private final PersonService personService;
    private final Validator validator;
    private final Converter<PersonDto, Person> personConverter;
    private final PersonAwsS3Uploader personAwsS3Uploader;

    @SneakyThrows
    @Override
    public APIGatewayProxyResponseEvent apply(Message<PersonDto> message) {
        PersonDto payload = message.getPayload();

        APIGatewayProxyResponseEvent responseEvent = validate(payload);
        if (responseEvent != null) {
            return responseEvent;
        }

        Person person = personConverter.convert(payload);

        Person createdPerson = personService.createPerson(person);

        personAwsS3Uploader.uploadFile(message, createdPerson);

        return createSuccessResponse(createdPerson.getId());
    }

    private APIGatewayProxyResponseEvent validate(PersonDto payload) {
        Set<ConstraintViolation<PersonDto>> constraintViolations = validator.validate(payload);

        if (!constraintViolations.isEmpty()) {
            return createBadRequestResponse();
        }
        return null;
    }

    private static APIGatewayProxyResponseEvent createSuccessResponse(UUID id) {
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();

        responseEvent.setStatusCode(HttpStatus.SC_CREATED);

        responseEvent.setBody(id.toString());

        return responseEvent;
    }

    private static APIGatewayProxyResponseEvent createBadRequestResponse() {
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setStatusCode(HttpStatus.SC_BAD_REQUEST);
        responseEvent.setBody("Invalid payload provided");
        return responseEvent;
    }

}
