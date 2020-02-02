package ood.repository;

import ood.model.Event;
import ood.model.Group;

import java.util.List;

public interface GroupDao {
    Group save(Group group);
    Group update(Group group);
    boolean delete(Group group);
    List<Event> getHistory(Group group);
}
