package ood.service;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.model.Voting;
import ood.repository.VotingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VotingService {
    @Autowired
    private VotingDao votingDao;

    @Autowired
    private GroupService groupService;

    public Voting save(Voting voting){
        return votingDao.save(voting);
    }

    public Voting update(Voting voting){
        return votingDao.update(voting);
    }

    public boolean delete(Voting voting){
        return votingDao.delete(voting);
    }

    public Voting getVotingById(long votingId){
        return votingDao.getVotingById(votingId);
    }

    public List<Voting> getVotingByStartTime(OffsetDateTime startTime){ return votingDao.getVotingByStartTime(startTime); }

    public List<Voting> getVotingByEndTime(OffsetDateTime endTime){ return votingDao.getVotingByEndTime(endTime); }

    public Voting createVoting(OffsetDateTime startTime, OffsetDateTime endTime, Event event, Group group){
        Voting voting = new Voting();
        voting.setStartTime(startTime);
        voting.setEndTime(endTime);
        voting.setVotingEvent(event);
        groupService.sendStartVotingEmail(group,event,voting);
        return votingDao.save(voting);
    }

    public Voting changeStartTime(Voting voting, OffsetDateTime startTime){
        voting.setStartTime(startTime);
        return votingDao.update(voting);
    }

    public Voting changeEndTime(Voting voting, OffsetDateTime endTime){
        voting.setEndTime(endTime);
        return votingDao.update(voting);
    }

    public Voting voteForMovie(User user, Voting voting, String ttId){
        String votingResultString = voting.getVotingResult();
        HashMap<String,String> votingResultMap = new HashMap<String,String>();
        String[] votingResultPairs = votingResultString.split(",");
        for (String pair: votingResultPairs) {
            String[] keyValue = pair.split(":");
            votingResultMap.put(keyValue[0], keyValue[1]);
        }
        String userId = Long.toString(user.getUserId());
        votingResultMap.put(userId,ttId);
        String votingResultStringNew = "";
        for(Map.Entry ele : votingResultMap.entrySet()){
            //"key1:val1,key2:val2"
            votingResultStringNew += ele.getKey() + ":" + ele.getValue() + ",";
        }
        votingResultStringNew = votingResultStringNew.substring(0,votingResultStringNew.length()-1);
        voting.setVotingResult(votingResultStringNew);
        return votingDao.update(voting);
    }
}
