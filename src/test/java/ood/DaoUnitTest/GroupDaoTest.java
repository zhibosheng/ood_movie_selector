package ood.DaoUnitTest;

import ood.ApplicationBoot;
import ood.model.Group;
import ood.model.User;
import ood.repository.GroupDaoImpl;
import ood.repository.UserDaoImpl;
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
public class GroupDaoTest {
    @Autowired
    private UserDaoImpl UserDao;
    @Autowired
    private GroupDaoImpl groupDao;

    private User userRecord1 = new User();
    private User userRecord2 = new User();

    private Group groupRecord1 = new Group();
    private Group groupRecord2 = new Group();


    @Before
    public void setup() {
        userRecord1.setUserName("Alice");
        userRecord1.setEmail("12324@qq.com");
        userRecord1.setPhone("2028538799");
        userRecord1.setPassword("123456");
        UserDao.save(userRecord1);

        userRecord2.setUserName("Bob");
        userRecord2.setEmail("7788@qq.com");
        userRecord2.setPhone("2097896879");
        userRecord2.setPassword("password");
        UserDao.save(userRecord2);

        groupRecord1.setModeratorId(userRecord1.getUserId());
    }
    @Test
    public void save(){
        groupDao.save(groupRecord1);
        Assert.assertNotNull(groupRecord1.getGroupId());
    }

    @Test
    public void update(){
        groupRecord1.setModeratorId(userRecord2.getUserId());
        groupDao.update(groupRecord1);
        Assert.assertEquals(groupRecord1.getModeratorId(),userRecord2.getUserId());
    }

    @Test
    public void delete(){
        groupDao.delete(groupRecord2);
    }

    @After
    public void cleanUp(){
        UserDao.delete(userRecord1);
        UserDao.delete(userRecord2);
        groupDao.delete(groupRecord1);
        if(groupRecord2 != null) groupDao.delete(groupRecord2);

    }
}
