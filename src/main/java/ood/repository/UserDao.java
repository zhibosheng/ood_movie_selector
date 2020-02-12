package ood.repository;

import ood.model.Group;
import ood.model.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    User update(User user);
    boolean delete(User user);
    User getUserByName(String userName);
    List<Group> getOwnGroups(User user);
    List<Group> getJoinGroups(User user);
}
