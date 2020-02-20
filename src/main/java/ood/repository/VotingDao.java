package ood.repository;

import ood.model.Voting;

public interface VotingDao {
    Voting save(Voting voting);
    Voting update(Voting voting);
    boolean delete(Voting voting);
    Voting getVotingById(long votingId);
}
