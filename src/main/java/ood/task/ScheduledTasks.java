package ood.task;


import ood.model.Event;
import ood.service.EventService;
import ood.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private EventService eventService;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkShowTime(){
        Date date = new Date();
        OffsetDateTime offsetDateTime = date.toInstant().atOffset(ZoneOffset.UTC)
        Event event = eventService.getEventByShowTime(offsetDateTime);

    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkStartVoting(){

    }
}
