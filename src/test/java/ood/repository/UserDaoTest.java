package ood.repository;

import ood.ApplicationBoot;
import ood.model.Group;
import ood.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class UserDaoTest {
    @Autowired
    private UserDaoImpl UserDao;
    private User userRecord1 = new User();
    private User userRecord2 = new User();
    @Autowired
    private GroupDaoImpl GroupDao;
    private Group groupRecord1 = new Group();
    private Group groupRecord2 = new Group();

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

        groupRecord1.setModerator(userRecord2);
        groupRecord1.setGroupName("groupA");
        groupRecord1.setGroupDescription("This is A");
        GroupDao.save(groupRecord1);
        groupRecord2.setModerator(userRecord2);
        groupRecord2.setGroupName("groupB");
        groupRecord2.setGroupDescription("This is B");
        GroupDao.save(groupRecord2);

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

    @Test
    public void getUserByName(){
        Assert.assertNotNull(UserDao.getUserByName("Bob"));
    }

    @Test
    public void getUserById(){
        long id = userRecord2.getUserId();
        Assert.assertEquals(UserDao.getUserById(id).getUserName(), "Bob");
    }

    @Test
    public void getUserByEmail(){
        String email = userRecord2.getEmail();
        Assert.assertEquals(UserDao.getUserByEmail(email).getUserName(), "Bob");
    }

    @Test
    public void getUserByPhone(){
        String phone = userRecord2.getPhone();
        Assert.assertEquals(UserDao.getUserByPhone(phone).getUserName(), "Bob");
    }

    @Test
    public void getOwnGroups(){
        List<Group> list = UserDao.getOwnGroups(userRecord2);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void getJoinGroups(){
        List<Group> l = new LinkedList<>();
        l.add(groupRecord1);
        l.add(groupRecord2);
        userRecord2.setJoinGroups(l);
        UserDao.update(userRecord2);

        List<Group> list = UserDao.getJoinGroups(userRecord2);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void getUserWithGroup(){
        long id = userRecord2.getUserId();
        Assert.assertEquals(UserDao.getUserWithGroup(id).getOwnGroups().size(), 2);
    }

    @Test
    public void getUserByCredentials(){
        String name = userRecord2.getUserName();
        String password = userRecord2.getPassword();
        Assert.assertEquals(UserDao.getUserByCredentials(name,password).getPhone(),userRecord2.getPhone());
        long id = userRecord2.getUserId();
        Assert.assertEquals(UserDao.getUserByCredentials(id,password).getPhone(),userRecord2.getPhone());
    }

    @After
    public void cleanUp(){
        if(UserDao.getOwnGroups(userRecord2)!=null) {
            GroupDao.delete(groupRecord1);
            GroupDao.delete(groupRecord2);
        }

        UserDao.delete(userRecord1);

        if(userRecord2 != null) UserDao.delete(userRecord2);

    }

}
