package ood.repository;

import ood.model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDaoImpl implements GroupDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Group save(Group group){
        return group;
    }

    @Override
    public Group update(Group group){
        return group;
    }

    @Override
    public boolean delete(Group group){
        return false;
    }
}
