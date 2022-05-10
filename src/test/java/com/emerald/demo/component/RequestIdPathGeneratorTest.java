package com.emerald.demo.component;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestIdPathGeneratorTest {
    public static final String AWS_REQUEST_ID = "awsRequestId";
    private final RequestIdPathGenerator pathGenerator = new RequestIdPathGenerator();
    @Mock
    private Message<?> message;

    @BeforeEach
    void setUp() {
        Context context = Mockito.mock(Context.class);
        when(context.getAwsRequestId())
                .thenReturn(AWS_REQUEST_ID);

        MessageHeaders messageHeaders = Mockito.mock(MessageHeaders.class);

        when(messageHeaders.get(RequestIdPathGenerator.AWS_CONTEXT_HEADER, Context.class))
                .thenReturn(context);

        when(message.getHeaders())
                .thenReturn(messageHeaders);
    }

    @Test
    void shouldGeneratePathSameAsRequestId() {
        String path = pathGenerator.generatePath(message);
        assertEquals(AWS_REQUEST_ID, path);
    }
}
