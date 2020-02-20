package ood.service;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.repository.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;

    public Group save(Group group){
        return groupDao.save(group);
    }

    public Group update(Group group){
        return groupDao.update(group);
    }

    public boolean delete(Group group){
        return groupDao.delete(group);
    }

    public Group getGroupById(long groupId){
        return groupDao.getGroupById(groupId);
    }

    public Group getGroupWithEvent(long groupId){
        return groupDao.getGroupWithEvent(groupId);
    }

    public Group createGroup(User user){
        Group group = new Group();
        group.setModerator(user);
        return groupDao.save(group);
    }

    public List<Event> getHistory(Group group){
        return groupDao.getHistory(group);
    }
}
