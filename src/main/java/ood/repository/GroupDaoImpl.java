package ood.repository;

import ood.model.Group;
import ood.model.User;
import ood.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDaoImpl implements GroupDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Group save(Group group){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(group);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (group!=null) logger.debug(String.format("The group %s was inserted into the table.", group.toString()));
        return group;
    }

    @Override
    public Group update(Group group){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(group);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (group!=null) logger.debug(String.format("The group %s was updated.", group.toString()));
        return group;
    }

    @Override
    public boolean delete(Group group){
        String hql = "DELETE Group where groupId = :id";
        int deletedCount = 0;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(hql);
            query.setParameter("id",group.getGroupId());
            deletedCount = query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug("The group was deleted");

        return deletedCount >= 1 ? true : false;
    }
}
