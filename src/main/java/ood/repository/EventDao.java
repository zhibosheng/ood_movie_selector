package ood.repository;

import ood.model.Event;

import java.time.OffsetDateTime;

public interface EventDao {
    Event save(Event event);
    Event update(Event event);
    boolean delete(Event event);
    Event getEventById(long eventId);
//    Event getEventByShowTime(OffsetDateTime showTime);
    Event getEventWithVoting(long eventId);
}
