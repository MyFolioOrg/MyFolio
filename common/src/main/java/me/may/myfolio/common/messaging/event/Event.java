package me.may.myfolio.common.messaging.event;

import com.google.gson.Gson;

import java.util.Optional;

public interface Event {
    default String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    static Optional<? extends Event> fromJson(String json, Class<? extends Event> eventClass) {
        try {
            Gson gson = new Gson();
            Event result = gson.fromJson(json, eventClass);
            return Optional.of(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
