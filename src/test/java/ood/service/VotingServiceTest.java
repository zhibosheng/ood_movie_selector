package ood.service;

import ood.ApplicationBoot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class VotingServiceTest {
    @Autowired
    private EventService eventService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private VotingService votingService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void createVoting(){
        OffsetDateTime startTime = OffsetDateTime.now().plusDays(1);
        OffsetDateTime endTime = startTime.plusDays(7);
        votingService.createVoting(startTime,endTime,eventService.getEventById(60),eventService.getEventWithGroup(60).getGroup());
    }
}
