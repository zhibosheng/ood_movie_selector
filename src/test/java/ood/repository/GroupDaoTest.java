package ood.repository;

import ood.ApplicationBoot;
import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class GroupDaoTest {
    @Autowired
    private UserDaoImpl UserDao;
    @Autowired
    private GroupDaoImpl groupDao;
    @Autowired
    private EventDaoImpl eventDao;

    private User userRecord1 = new User();
    private User userRecord2 = new User();

    private Group groupRecord1 = new Group();
    private Group groupRecord2 = new Group();

    private Event eventRecord1 = new Event();
    private Event eventRecord2 = new Event();

//    SessionFactory sessionFactory = null;
//    Session session = null;


    @Before
    public void setup() {
//        userRecord1.setUserName("Alice");
//        userRecord1.setEmail("12324@qq.com");
//        userRecord1.setPhone("2028538799");
//        userRecord1.setPassword("123456");
//        UserDao.save(userRecord1);



//        userRecord2.setUserName("Bob");
//        userRecord2.setEmail("7788@qq.com");
//        userRecord2.setPhone("2097896879");
//        userRecord2.setPassword("password");
//        UserDao.save(userRecord2);
//
//
//
//
//        groupRecord2.setModerator(userRecord2);
//        groupRecord2.setGroupName("groupB");
//        groupRecord2.setGroupDescription("This is B");
//        groupDao.save(groupRecord2);
//
//        groupRecord1.setModerator(userRecord1);
//        groupRecord1.setGroupName("groupA");
//        groupRecord1.setGroupDescription("This is A");
//        List<Event> list = new LinkedList<>();
//        list.add(eventRecord1);
//        list.add(eventRecord2);
//        groupRecord1.setEvents(list);
////
//        groupDao.save(groupRecord1);

//        List<Group> joinGroupsList = UserDao.getJoinGroups(userRecord1);
//        joinGroupsList.add(groupRecord1);
//        userRecord1.setJoinGroups(joinGroupsList);
//        UserDao.update(userRecord1);


//        eventRecord1.setMovieDecision("1917");
//        eventRecord1.setGroup(groupRecord1);
//        eventRecord1.setCreateTime(OffsetDateTime.now());
//        eventRecord1.setShowTime(OffsetDateTime.parse("2020-05-20T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
//
//
//        eventRecord2.setCreateTime(OffsetDateTime.now());
//        eventRecord2.setGroup(groupRecord1);
//        eventRecord2.setShowTime(OffsetDateTime.parse("2020-05-21T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
//        eventRecord2.setMovieDecision("black mirror");
    }
    @Test
    public void save(){
        groupDao.save(groupRecord1);
        Assert.assertNotNull(groupRecord1.getGroupId());
    }

    @Test
    public void update(){
        groupRecord1.setModerator(userRecord2);
        groupDao.update(groupRecord1);
        Assert.assertEquals(groupRecord1.getModerator(),userRecord2);
    }

    @Test
    public void delete(){
        groupDao.delete(groupRecord2);
        Assert.assertEquals(0,groupDao.getAllGroups().size());
    }

    @Test
    public void getGroupById(){
        long id = groupRecord2.getGroupId();
        Assert.assertEquals(groupDao.getGroupById(id).getGroupName(), "groupB");

    }

    @Test
    public void getGroupWithEvent(){
        Group g = groupDao.getGroupWithEvent(253);
        System.out.println(g.getLastEvent().getShowTime());

    }


    @Test
    public void getAllGroups(){
        Assert.assertEquals(groupDao.getAllGroups().size(), 1);
    }

    @Test
    public void getHistory(){

        groupDao.save(groupRecord1);
        eventDao.save(eventRecord1);
        eventDao.save(eventRecord2);

        Assert.assertEquals(2,groupDao.getHistory(groupRecord1).size());

    }





    @After
    public void cleanUp(){
        //eventDao.delete(eventRecord1);
        //eventDao.delete(eventRecord2);
        //groupDao.delete(groupRecord1);
        //if(groupRecord2 != null) groupDao.delete(groupRecord2);
        //UserDao.delete(userRecord1);
        //UserDao.delete(userRecord2);
    }

}
