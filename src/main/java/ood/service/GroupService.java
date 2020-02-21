package ood.service;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.model.Voting;
import ood.repository.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MovieAPIService movieAPIService;

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
