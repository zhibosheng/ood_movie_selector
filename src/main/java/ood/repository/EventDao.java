package ood.repository;

import ood.model.Event;

import java.time.OffsetDateTime;
import java.util.List;

public interface EventDao {
    Event save(Event event);
    Event update(Event event);
    boolean delete(Event event);
    Event getEventById(long eventId);
    List<Event> getEventByShowTime(OffsetDateTime showTime);
    Event getEventWithVoting(long eventId);
    Event getEventWithGroup(long eventId);
}
