package ood.service;

import ood.ApplicationBoot;
import ood.model.Event;
import ood.repository.EventDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class EventServiceTest {
    @Autowired
    private EventService eventService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private EventDao eventDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //private Event eventRecord1 = new Event();

    @Test
    public void createEvent(){
        OffsetDateTime showTime = OffsetDateTime.now().plusDays(3);
        eventService.createEvent(groupService.getGroupByName("test"), showTime);
    }

    @Test
    public void changeShowTime(){
        OffsetDateTime showtime = OffsetDateTime.now().plusDays(7);
        eventService.changeShowTime(eventService.getEventById(59),showtime);

    }
}
