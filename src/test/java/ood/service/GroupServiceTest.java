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
        userRecord1.setUserName("Alice");
        userRecord1.setEmail("12324@qq.com");
        userRecord1.setPhone("2028538799");
        userRecord1.setPassword("123456");
        userService.save(userRecord1);
        groupService.createGroup(userRecord1,"groupA","This is A");
        Set<Group> ownGroups =  userService.getOwnGroups(userRecord1);
        Set<Group> joinGroups = userService.getJoinGroups(userRecord1);
        System.out.println("check groups");
    }

}
