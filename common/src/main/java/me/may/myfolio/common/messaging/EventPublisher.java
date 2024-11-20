package me.may.myfolio.common.messaging;

import me.may.myfolio.common.messaging.event.Event;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface EventPublisher<T extends Event> {
    default void publish(T event) throws IOException, TimeoutException {
        doPublish(event.toJson());
    }

    void doPublish(String eventJson) throws IOException, TimeoutException;
}
