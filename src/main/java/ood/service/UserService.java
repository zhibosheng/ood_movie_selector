package ood.service;

import ood.model.User;
import ood.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao dao;

    public User save(User user){
        return dao.save(user);
    }

    public User update(User user){
        return dao.update(user);
    }

    public boolean delete(User user){
        return dao.delete(user);
    }
}
