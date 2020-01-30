package ood.service;

import ood.model.Group;
import ood.repository.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    private GroupDao dao;

    public Group save(Group group){
        return dao.save(group);
    }

    public Group update(Group group){
        return dao.update(group);
    }

    public boolean delete(Group group){
        return dao.delete(group);
    }
}
