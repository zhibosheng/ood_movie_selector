package ood.repository;

import ood.model.Voting;

import java.time.OffsetDateTime;

public interface VotingDao {
    Voting save(Voting voting);
    Voting update(Voting voting);
    boolean delete(Voting voting);
    Voting getVotingById(long votingId);
//    Voting getVotingByStartTime(OffsetDateTime startTime);
//    Voting getVotingByEndTime(OffsetDateTime endTime);
}
