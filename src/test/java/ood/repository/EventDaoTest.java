package ood.repository;

import ood.ApplicationBoot;
import ood.model.Event;
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
    private EventDaoImpl eventDao;
    private Event eventRecord1 = new Event();
    private Event eventRecord2 = new Event();

    @Before
    public void setup() {

        eventRecord1.setCreateTime(OffsetDateTime.now());
        eventRecord1.setShowTime(OffsetDateTime.parse("2020-05-20T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        eventRecord1.setMovieDecision("Black Mirror");

        eventRecord1.setCreateTime(OffsetDateTime.now());
        eventRecord1.setShowTime(OffsetDateTime.parse("2020-05-21T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        eventRecord1.setMovieDecision("1917");
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

    @After
    public void cleanUp(){
        eventDao.delete(eventRecord1);
        if(eventRecord2 != null) eventDao.delete(eventRecord2);
    }
}
