package ood.repository;

import ood.model.Event;

public interface EventDao {
    Event save(Event event);
    Event update(Event event);
    boolean delete(Event event);
}
