package ood.service;

import ood.ApplicationBoot;
import ood.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private User userRecord1 = new User();

    @Before
    public void setup() {
        userRecord1.setUserName("Alice");
        userRecord1.setEmail("12324@qq.com");
        userRecord1.setPhone("2028538799");
        userRecord1.setPassword("123456");
        userService.save(userRecord1);
    }

    @Test
    public void getUserByCredentials(){
        try {
            User u = userService.getUserByCredentials("dwang", "12345678");
            logger.info(u.toString());
            User u2 = userService.getUserByCredentials(155, "12345678");
            logger.info(u2.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            if (msg == null) msg = "BAD REQUEST!";
            logger.error(msg);
        }

        Assert.assertEquals(userService.getUserByCredentials(userRecord1.getUserName(), userRecord1.getPassword()).getPhone(), "2028538799");
    }

    @After
    public void cleanUp(){
        userService.delete(userRecord1);
    }
}
