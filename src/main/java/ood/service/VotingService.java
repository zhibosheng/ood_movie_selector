package ood.service;

import ood.model.Event;
import ood.model.Group;
import ood.model.Voting;
import ood.repository.VotingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

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
}
