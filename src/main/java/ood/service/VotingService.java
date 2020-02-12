package ood.service;

import ood.model.Event;
import ood.model.Voting;
import ood.repository.VotingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class VotingService {
    @Autowired
    private VotingDao votingDao;

    public Voting save(Voting voting){
        return votingDao.save(voting);
    }

    public Voting update(Voting voting){
        return votingDao.update(voting);
    }

    public boolean delete(Voting voting){
        return votingDao.delete(voting);
    }

    public Voting createVoting(OffsetDateTime startTime, OffsetDateTime endTime, Event event){
        Voting voting = new Voting();
        voting.setStartTime(startTime);
        voting.setEndTime(endTime);
        voting.setVotingEvent(event);
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
