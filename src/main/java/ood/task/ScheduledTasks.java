package ood.task;


import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.model.Voting;
import ood.service.EventService;
import ood.service.GroupService;
import ood.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private GroupService groupService;

    @Autowired
    private EventService eventService;

    @Autowired
    private VotingService votingService;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkShowTime(){
        Date date = new Date();
        OffsetDateTime offsetDateTime = date.toInstant().atOffset(ZoneOffset.UTC);
        List<Event> eventList = eventService.getEventByShowTime(offsetDateTime);
        for(Event event: eventList){
            Group group = event.getGroup();
            groupService.sendMovieStartNotificationEmail(group,event);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkStartVoting(){
        Date date = new Date();
        OffsetDateTime offsetDateTime = date.toInstant().atOffset(ZoneOffset.UTC);
        List<Voting> votingList = votingService.getVotingByStartTime(offsetDateTime);
        for(Voting voting: votingList){
            Event event = voting.getVotingEvent();
            Group group = event.getGroup();
            List<User> userList = group.getUsers();
            HashMap<String,String> votingResultMap = new HashMap<>();
            for(User user: userList){
                votingResultMap.put(Long.toString(user.getUserId()),"");
            }

            groupService.sendStartVotingEmail(group,event,voting);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkEndVoting(){
        Date date = new Date();
        OffsetDateTime offsetDateTime = date.toInstant().atOffset(ZoneOffset.UTC);
        List<Voting> votingList = votingService.getVotingByEndTime(offsetDateTime);
        for(Voting voting: votingList){
            Event event = voting.getVotingEvent();
            Group group = event.getGroup();
            //TO DO calculate voting result and save to database
            groupService.sendVotingResultEmail(group,event,voting);
        }
    }
}
