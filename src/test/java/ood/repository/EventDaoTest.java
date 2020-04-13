package ood.repository;

import ood.ApplicationBoot;
import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.model.Voting;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class EventDaoTest {
    @Autowired
    private VotingDaoImpl votingDao;
    private Voting votingRecord1 = new Voting();

    @Autowired
    private EventDaoImpl eventDao;
    private Event eventRecord1 = new Event();
    private Event eventRecord2 = new Event();

    @Autowired
    private GroupDaoImpl groupDao;
    private Group groupRecord1 = new Group();

    @Autowired
    private UserDaoImpl UserDao;
    private User userRecord1 = new User();

    @Before
    public void setup() {
        userRecord1.setUserName("Alice");
        userRecord1.setEmail("12324@qq.com");
        userRecord1.setPhone("2028538799");
        userRecord1.setPassword("123456");
        UserDao.save(userRecord1);

        groupRecord1.setModerator(userRecord1);
        groupRecord1.setGroupName("groupA");
        groupRecord1.setGroupDescription("This is A");
        groupDao.save(groupRecord1);

        eventRecord1.setCreateTime(OffsetDateTime.now());
        eventRecord1.setGroup(groupRecord1);
        eventRecord1.setShowTime(OffsetDateTime.parse("2020-05-20T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        eventRecord1.setMovieDecision("Black Mirror");

        votingRecord1.setStartTime(OffsetDateTime.parse("2020-05-20T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        votingRecord1.setEndTime(OffsetDateTime.parse("2020-05-21T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        votingRecord1.setVotingResult("X man");
        votingDao.save(votingRecord1);

        eventRecord2.setCreateTime(OffsetDateTime.now());
        eventRecord2.setGroup(groupRecord1);
        eventRecord2.setShowTime(OffsetDateTime.parse("2020-05-21T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        eventRecord2.setMovieDecision("1917");
        eventRecord2.setVoting(votingRecord1);
        eventDao.save(eventRecord2);

    }
    @Test
    public void save(){
        eventDao.save(eventRecord1);
        Assert.assertNotNull(eventRecord1.getEventId());
    }

    @Test
    public void update(){
        eventRecord1.setMovieDecision("Star Wars");
        eventDao.update(eventRecord1);
        Assert.assertEquals(eventRecord1.getMovieDecision(),"Star Wars");
    }

    @Test
    public void delete(){
        eventDao.delete(eventRecord2);
    }

    @Test
    public void getEventById(){
//        long id = eventRecord2.getEventId();
//        Assert.assertEquals(eventDao.getEventById(id).getMovieDecision(), "1917");
        Event e = eventDao.getEventWithGroup(60);
        System.out.println(e.getGroup().getGroupName() + " " + e.getGroup().getGroupDescription());
    }

    @Test
    public void getEventWithVoting(){

//        long id = eventRecord2.getEventId();
//        Assert.assertEquals(eventDao.getEventWithVoting(id).getVoting().getVotingResult(), "X man");

        Event e = eventDao.getEventWithVoting(60);
        System.out.println(e.getVoting().getStartTime());
        System.out.println(e.getVoting().getEndTime());

    }

    @Test
    public void getEventByShowTime(){
        Assert.assertEquals(eventDao.getEventByShowTime(OffsetDateTime.parse("2020-05-21T16:30:00-04:00")).size(), 1);
    }

    @After
    public void cleanUp(){
        eventDao.delete(eventRecord1);
        if(eventRecord2 != null) eventDao.delete(eventRecord2);
        groupDao.delete(groupRecord1);
        UserDao.delete(userRecord1);
        votingDao.delete(votingRecord1);
    }
}
