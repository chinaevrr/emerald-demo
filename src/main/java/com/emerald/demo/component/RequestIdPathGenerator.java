package com.emerald.demo.component;


import com.amazonaws.services.lambda.runtime.Context;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class RequestIdPathGenerator implements PathGenerator {
    static final String AWS_CONTEXT_HEADER = "aws-context";

    @Override
    public String generatePath(Message<?> message) {
        Context context = getContext(message);
        return context.getAwsRequestId();
    }

    @NonNull
    private Context getContext(Message<?> message) {
        Context context = message.getHeaders().get(AWS_CONTEXT_HEADER, Context.class);
        Assert.notNull(context, "Message should contain context");
        return context;
    }
}
