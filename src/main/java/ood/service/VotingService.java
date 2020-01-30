package ood.service;

import ood.model.Voting;
import ood.repository.VotingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingService {
    @Autowired
    private VotingDao dao;

    public Voting save(Voting voting){
        return dao.save(voting);
    }

    public Voting update(Voting voting){
        return dao.update(voting);
    }

    public boolean delete(Voting voting){
        return dao.delete(voting);
    }
}
