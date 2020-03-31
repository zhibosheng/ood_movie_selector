package ood.repository;


import ood.model.User;
import ood.model.Voting;
import ood.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class VotingDaoImpl implements VotingDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Voting save(Voting voting){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(voting);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (voting!=null) logger.debug(String.format("The voting %s was inserted into the table.", voting.toString()));
        return voting;
    }

    @Override
    public Voting update(Voting voting){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(voting);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (voting!=null) logger.debug(String.format("The voting %s was updated.", voting.toString()));
        return voting;
    }

    @Override
    public boolean delete(Voting voting) {
        String hql = "DELETE Voting where votingId = :id";
        int deletedCount = 0;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Voting> query = session.createQuery(hql);
            query.setParameter("id",voting.getVotingId());
            deletedCount = query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug("The voting was deleted");

        return deletedCount >= 1 ? true : false;
    }

    @Override
    public Voting getVotingById(long votingId) {

        String hql = "FROM Voting where votingId = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Voting> query = session.createQuery(hql);
            query.setParameter("id", votingId);

            Voting voting = query.uniqueResult();
            if (voting != null) {
                logger.debug(voting.toString());
            }
            return voting;
        }
    }
}
