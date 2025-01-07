package me.may.myfolio.common.messaging.event;

public record SuspiciousLoginEvent(String email, String location, String operatingSystem, boolean successful) implements Event { }

