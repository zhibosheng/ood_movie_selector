package ood.repository;

import ood.model.Group;

public interface GroupDao {
    Group save(Group group);
    Group update(Group group);
    boolean delete(Group group);
}
