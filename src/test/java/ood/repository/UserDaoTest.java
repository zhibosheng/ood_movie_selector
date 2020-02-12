package ood.repository;

import ood.ApplicationBoot;
import ood.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class UserDaoTest {
    @Autowired
    private UserDaoImpl UserDao;
    private User userRecord1 = new User();
    private User userRecord2 = new User();
    @Before
    public void setup() {

        userRecord1.setUserName("Alice");
        userRecord1.setEmail("12324@qq.com");
        userRecord1.setPhone("2028538799");
        userRecord1.setPassword("123456");

        userRecord2.setUserName("Bob");
        userRecord2.setEmail("7788@qq.com");
        userRecord2.setPhone("2097896879");
        userRecord2.setPassword("password");
        UserDao.save(userRecord2);
    }
    @Test
    public void save(){
        UserDao.save(userRecord1);
        Assert.assertNotNull(userRecord1.getUserId());
    }

    @Test
    public void update(){
        userRecord1.setPassword("654321");
        UserDao.update(userRecord1);
        Assert.assertEquals(userRecord1.getPassword(),"654321");
    }

    @Test
    public void delete(){
       UserDao.delete(userRecord2);
    }

    @After
    public void cleanUp(){
        UserDao.delete(userRecord1);
        if(userRecord2 != null) UserDao.delete(userRecord2);
    }

}
