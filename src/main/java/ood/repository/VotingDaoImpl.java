package ood.repository;


import ood.model.Voting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class VotingDaoImpl implements VotingDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Voting save(Voting voting){
        return voting;
    }

    @Override
    public Voting update(Voting voting){
        return voting;
    }

    @Override
    public boolean delete(Voting voting) {
        return false;
    }
}
