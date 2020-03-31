package ood.repository;

import ood.model.Voting;

import java.time.OffsetDateTime;
import java.util.List;

public interface VotingDao {
    Voting save(Voting voting);
    Voting update(Voting voting);
    boolean delete(Voting voting);
    Voting getVotingById(long votingId);
    List<Voting> getVotingByStartTime(OffsetDateTime startTime);
    List<Voting> getVotingByEndTime(OffsetDateTime endTime);
}
