package ood.repository;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

public interface GroupDao {
    Group save(Group group);
    Group update(Group group);
    boolean delete(Group group);
    Group getGroupById(long groupId);
    Group getGroupWithEvent(long groupId);
    List<Group> getAllGroups();
    List<Event> getHistory(Group group);
    Set<User> getUsers(Group group);
    //Group changeModerator(Group group, User user);

    //List<User> getGroupWithUsers(Group group);
}
