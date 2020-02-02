package ood.repository;

import ood.model.User;
import ood.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository
public class UserDaoImpl implements UserDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public User save(User user){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (user!=null) logger.info(String.format("The user %s was inserted into the table.", user.toString()));

        return user;
    }

    @Override
    public User update(User user){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (user!=null) logger.debug(String.format("The user %s was updated.", user.toString()));
        return user;
    }

    @Override
    public boolean delete(User user) {
        String hql = "DELETE User where userId = :id";
        int deletedCount = 0;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(hql);
            query.setParameter("id",user.getUserId());
            deletedCount = query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug("The user was deleted");

        return deletedCount >= 1 ? true : false;

    }
}
