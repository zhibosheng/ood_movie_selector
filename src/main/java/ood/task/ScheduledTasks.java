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
import java.util.*;

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
        System.out.println("time now is：" + dateFormat.format(new Date()));
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
            Set<User> userList = group.getUsers();
            HashMap<String,String> votingResultMap = new HashMap<String,String>();
            for(User user: userList){
                votingResultMap.put(Long.toString(user.getUserId()),"None");
            }
            String votingResultString = "";
            for(Map.Entry ele : votingResultMap.entrySet()){
                //"key1:val1,key2:val2"
                votingResultString += ele.getKey() + ":" + ele.getValue() + ",";
            }
            votingResultString = votingResultString.substring(0,votingResultString.length()-1);
            voting.setVotingResult(votingResultString);
            votingService.update(voting);
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
            String votingResultString = voting.getVotingResult();
            HashMap<String,String> votingResultMap = new HashMap<String,String>();
            HashMap<String,Integer> movieCountMap = new HashMap<String,Integer>();
            String[] votingResultPairs = votingResultString.split(",");
            for (String pair: votingResultPairs){
                String[] keyValue = pair.split(":");
                String userId = keyValue[0];
                String ttId = keyValue[1];
                votingResultMap.put(userId,ttId);
                if(movieCountMap.containsKey(ttId)){
                    movieCountMap.put(ttId,movieCountMap.get(ttId)+1);
                }else{
                    movieCountMap.put(ttId,1);
                }
            }
            String winnerMovie = "";
            Integer maxCount = 0;
            for(Map.Entry ele : movieCountMap.entrySet()){
                String ttId = ele.getKey().toString();
                Integer count = (Integer) ele.getValue();
                if(count > maxCount){
                    winnerMovie = ttId;
                    maxCount = count;
                }
            }
            event.setMovieDecision(winnerMovie);
            eventService.update(event);
            groupService.sendVotingResultEmail(group,event,voting);
        }
    }
}
