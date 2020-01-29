package ood.repository;

import ood.model.User;

public interface UserDao {
    User save(User user);
    User update(User user);
    boolean delete(User user);
}
