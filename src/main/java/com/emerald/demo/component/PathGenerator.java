package com.emerald.demo.component;

import org.springframework.messaging.Message;

public interface PathGenerator {
    String generatePath(Message<?> message);
}
