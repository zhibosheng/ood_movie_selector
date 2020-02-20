package ood.repository;

import ood.model.Event;

public interface EventDao {
    Event save(Event event);
    Event update(Event event);
    boolean delete(Event event);
    Event getEventById(long eventId);
    Event getEventWithVoting(long eventId);
}
