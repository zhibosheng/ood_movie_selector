package ood.service;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.model.Voting;
import ood.repository.GroupDao;
import ood.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

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

    public Group getGroupByName(String groupName){
        return groupDao.getGroupByName(groupName);
    }

    public Group getGroupWithEvent(long groupId){
        return groupDao.getGroupWithEvent(groupId);
    }

    public Group getGroupWithUser(long groupId){
        return groupDao.getGroupWithUser(groupId);
    }

    public Group createGroup(User user,String groupName, String groupDescription){
        Group group = new Group();
        group.setModerator(user);
        group.setGroupName(groupName);
        group.setGroupDescription(groupDescription);
        Group res = groupDao.save(group);
//        Set<Group> ownGroupSet = userService.getOwnGroups(user);
//        ownGroupSet.add(res);
//        user.setOwnGroups(ownGroupSet);
        //userService.update(user);
       // Set<Group> joinGroupSet = userService.getJoinGroups(user);
       // joinGroupSet.add(res);
        //user.setJoinGroups(joinGroupSet);
        userDao.addJoinGroup(userDao.getUserByName(user.getUserName()),groupService.getGroupByName(groupName));
        //userService.update(user);
        return res;
    }

    public List<Event> getHistory(Group group){
        return groupDao.getHistory(group);
    }

    public boolean sendInviteGroupEmail(Group group, String email){
        boolean flag = true;
        String TO = email;
        String SUBJECT = "Group invitation from MovieSelector Website";
        String HTMLBODY = "<h1>Hi,</h1>" +
                        "<p>Your friend is inviting you to join his/her movie watching group. " +
                        "Please click the following link: <a href='https://aws.amazon.com/ses/'> click me </a>. " +
                        "If you are a new user, " +
                        "you are welcome to click to register a new account for free. " +
                        "If you already have an account, please log in to join this new group.</p>";
        String TEXTBODY =  "Hi, Your friend is inviting you to join his/her movie watchinggroup.";

        try {
            messageService.sendEmail(TO, SUBJECT, HTMLBODY, TEXTBODY);
        }catch (Exception ex){
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
            flag = false;
        }
        return flag;
    }

//    public List<User> getGroupWithUsers(Group group){ return groupDao.getGroupWithUsers(group);}

    public HashMap getDefaultMovies(){
        return movieAPIService.getDefaultMovies();
    }

    public boolean sendStartEventEmail(Group group, Event event){
        boolean flag = true;
        String SUBJECT = "New group event notification from MovieSelector Website";
        String HTMLBODY = "<h1>Hi,</h1>" +
                          "<p>" + group.getGroupName() + " has a new event, which is created by " + group.getModerator() +". "+
                          "This movie watching eventwill start at " + event.getShowTime() +
                          "Hope you can participate.</p>";
        String TEXTBODY = "Hi, " +
                          group.getGroupName() + " has a new event, which is created by " + group.getModerator() + ". "+
                          "This movie watching eventwill start at " + event.getShowTime() + "." +
                          "Hope you can participate.";
        Set<User> users = groupDao.getUsers(group);
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
        String SUBJECT = "New voting invitation from MovieSelector Website";
        String HTMLBODY = "<h1>Hi,</h1>" +
                          "<p>" + group.getModerator() + " in " + group.getGroupName() + " you joined has initiated a voting, " +
                          "please clickthe link: <a href='https://aws.amazon.com/ses/'> click me </a> to participate in the voting. " +
                          "Voting will end at " + voting.getEndTime() + ", " + "please complete the voting before the end time. " +
                          "Your vote will determine the final viewing result.</p>";
        String TEXTBODY = "Hi, " +
                          group.getModerator() + " in " + group.getGroupName() + " you joined has initiated a voting, " +
                          "please clickthe link: <a href='https://aws.amazon.com/ses/'> click me </a> to participate in the voting. " +
                          "Voting will end at " + voting.getEndTime() + ", " + "please complete the voting before the end time. " +
                          "Your vote will determine the final viewing result.";;
        Set<User> users = groupDao.getUsers(group);
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
        String SUBJECT = "Announcement of the voting result from MovieSelector Website";
        String HTMLBODY = "<h1>Hi,</h1>" +
                          "<p>Voting on the " + group.getGroupName() + " you joined has ended. " +
                          "As a result, " + event.getMovieDecision() + " became the movie of choice.</p>";
        String TEXTBODY = "Hi, " +
                          "Voting on the " + group.getGroupName() + " you joined has ended. " +
                          "As a result, " + event.getMovieDecision() + " became the movie of choice.";;
        Set<User> users = groupDao.getUsers(group);
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
        String SUBJECT = "Notification of the upcoming event from MovieSelector Website";
        String HTMLBODY = "<h1>Hi,</h1>" +
                          "<p>" + group.getGroupName() + " will start watching the " + event.getMovieDecision() + " in ten minutes." +
                          "Hope you will be on time.</p>";
        String TEXTBODY = "Hi, " +
                          group.getGroupName() + " will start watching the " + event.getMovieDecision() + " in ten minutes." +
                          "Hope you will be on time.";
        Set<User> users = groupDao.getUsers(group);
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
