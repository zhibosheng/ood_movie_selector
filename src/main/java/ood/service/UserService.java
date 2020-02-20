package ood.service;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.model.Voting;
import ood.repository.GroupDao;
import ood.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private MessageService messageService;

    public User save(User user){
        return userDao.save(user);
    }

    public User update(User user){
        return userDao.update(user);
    }

    public boolean delete(User user){
        return userDao.delete(user);
    }

    public User getUserById(long userId){
        return userDao.getUserById(userId);
    }

    public User getUserByName(String userName){
        return userDao.getUserByName(userName);
    }

    public User getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

    public User getUserByPhone(String phone){
        return getUserByPhone(phone);
    }

    public List<Group> getOwnGroups(User user){
        return userDao.getOwnGroups(user);
    }

    public List<Group> getJoinGroups(User user){
        return userDao.getJoinGroups(user);
    }

    public User addOwnGroup(User user){
        Group group = groupService.createGroup(user);
        return userService.addOwnGroup(user,group);
    }


    public User addOwnGroup(User user,Group group){
        List<Group> ownGroupsList = userDao.getOwnGroups(user);
        ownGroupsList.add(group);
        user.setOwnGroups(ownGroupsList);
        return userDao.update(user);
    }

    public User removeOwnGroup(User user,Group group){
        List<Group> ownGroupsList = userDao.getOwnGroups(user);
        ownGroupsList.remove(group);
        user.setOwnGroups(ownGroupsList);
        groupService.delete(group);
        return userDao.update(user);
    }

    public User joinGroup(User user,Group group){
        List<Group> joinGroupsList = userDao.getJoinGroups(user);
        joinGroupsList.add(group);
        user.setJoinGroups(joinGroupsList);
        return userDao.update(user);
    }

    public User leaveGroup(User user,Group group){
        List<Group> joinGroupsList = userDao.getJoinGroups(user);
        joinGroupsList.remove(group);
        user.setJoinGroups(joinGroupsList);
        return userDao.update(user);
    }

    public User getUserWithGroup(long userId){
        return getUserWithGroup(userId);
    }

    public boolean sendInviteGroupEmail(Group group, String email){
        boolean flag = true;
        String TO = email;
        String SUBJECT = "";
        String HTMLBODY = "";
        String TEXTBODY = "";
        try {
            messageService.sendEmail(TO, SUBJECT, HTMLBODY, TEXTBODY);
        }catch (Exception ex){
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
            flag = false;
        }
        return flag;
    }

    public boolean sendStartEventEmail(Group group, Event event){
        boolean flag = true;
        String SUBJECT = "";
        String HTMLBODY = "";
        String TEXTBODY = "";
        List<User> users = group.getUsers();
        for(User user:users){
            String TO = user.getEmail();
            try {
                messageService.sendEmail(TO, SUBJECT, HTMLBODY, TEXTBODY);
            }catch (Exception ex){
                System.out.println("The email was not sent. Error message: "
                        + ex.getMessage());
                flag = false;
            }
        }
        return flag;
    }

    public boolean sendStartVotingEmail(Group group, Event event, Voting voting){
        boolean flag = true;
        String SUBJECT = "";
        String HTMLBODY = "";
        String TEXTBODY = "";
        List<User> users = group.getUsers();
        for(User user:users){
            String TO = user.getEmail();
            try {
                messageService.sendEmail(TO, SUBJECT, HTMLBODY, TEXTBODY);
            }catch (Exception ex){
                System.out.println("The email was not sent. Error message: "
                        + ex.getMessage());
                flag = false;
            }
        }
        return flag;
    }

    public boolean sendVotingResultEmail(Group group, Event event, Voting voting){
        boolean flag = true;
        String SUBJECT = "";
        String HTMLBODY = "";
        String TEXTBODY = "";
        List<User> users = group.getUsers();
        for(User user:users){
            String TO = user.getEmail();
            try {
                messageService.sendEmail(TO, SUBJECT, HTMLBODY, TEXTBODY);
            }catch (Exception ex){
                System.out.println("The email was not sent. Error message: "
                        + ex.getMessage());
                flag = false;
            }
        }
        return flag;
    }

    public boolean sendMovieStartNotificationEmail(Group group, Event event){
        boolean flag = true;
        String SUBJECT = "";
        String HTMLBODY = "";
        String TEXTBODY = "";
        List<User> users = group.getUsers();
        for(User user:users){
            String TO = user.getEmail();
            try {
                messageService.sendEmail(TO, SUBJECT, HTMLBODY, TEXTBODY);
            }catch (Exception ex){
                System.out.println("The email was not sent. Error message: "
                        + ex.getMessage());
                flag = false;
            }
        }
        return flag;
    }





}
