package ood.repository;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;

import java.util.List;

public interface GroupDao {
    Group save(Group group);
    Group update(Group group);
    boolean delete(Group group);
    Group getGroupById(long groupId);
    Group getGroupWithEvent(long groupId);
    List<Group> getAllGroups();
    List<Event> getHistory(Group group);
}
