package ood.repository;


import ood.model.Event;
import ood.model.User;
import ood.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl implements EventDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Event save(Event event){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(event);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (event!=null) logger.debug(String.format("The event %s was inserted into the table.", event.toString()));
        return event;
    }

    @Override
    public Event update(Event event){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(event);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (event!=null) logger.debug(String.format("The event %s was updated.", event.toString()));
        return event;
    }

    @Override
    public boolean delete(Event event) {
        String hql = "DELETE Event where eventId = :id";
        int deletedCount = 0;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(hql);
            query.setParameter("id",event.getEventId());
            deletedCount = query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug("The event was deleted");

        return deletedCount >= 1 ? true : false;
    }
}
