package ood.service;


import ood.ApplicationBoot;
import ood.model.Group;
import ood.model.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class GroupServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private User userRecord1 = new User();
    private Group groupRecord1 = new Group();

    @Test
    public void createGroup(){
//        userRecord1.setUserName("Bob");
//        userRecord1.setEmail("7788@qq.com");
//        userRecord1.setPhone("2097896879");
//        userRecord1.setPassword("password");
//        userService.save(userRecord1);
        groupService.createGroup(userService.getUserByName("Bob"),"Bob's group8","This is bob group8");
        Set<Group> ownGroups =  userService.getOwnGroups(userService.getUserByName("Bob"));
        for(Group g : ownGroups){
            System.out.println(g.getGroupName());
        }
        Set<Group> joinGroups = userService.getJoinGroups(userService.getUserByName("Bob"));
        for(Group g : joinGroups){
            System.out.println(g.getGroupName());
        }
        System.out.println("check groups");

    }

    @Test
    public void getGroupWithEvent(){

    }

    
}
