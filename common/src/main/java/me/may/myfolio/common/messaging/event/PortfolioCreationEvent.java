package me.may.myfolio.common.messaging.event;

public record PortfolioCreationEvent(String title, long id, long ownerId, String content) implements Event { }
