package ood.repository;

import ood.model.Group;
import ood.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    User save(User user);
    User update(User user);
    boolean delete(User user);
    User getUserById(long userId);
    User getUserByName(String userName);
    User getUserByEmail(String email);
    User getUserByPhone(String phone);
    Set<Group> getOwnGroups(User user);
    Set<Group> getJoinGroups(User user);
    User getUserWithGroup(long userId);
    User getUserByCredentials(String name, String password);
    User getUserByCredentials(long userId, String password);
    User addOwnGroup(User user, Group group);
    User deleteOwnGroup(User user, Group group);
    User addJoinGroup(User user, Group group);
    User leaveJoinGroup(User user, Group group);
}
