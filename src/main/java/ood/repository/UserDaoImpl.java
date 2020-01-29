package ood.repository;

import ood.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sun.rmi.runtime.Log;

@Repository
public class UserDaoImpl implements UserDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User save(User user){
        return user;
    }

    @Override
    public User update(User user){
        return user;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
