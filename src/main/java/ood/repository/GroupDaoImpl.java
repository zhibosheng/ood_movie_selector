package ood.repository;

import ood.model.Event;
import ood.model.Group;
import ood.model.User;
import ood.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            Query<Group> query = session.createQuery(hql);
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

    @Override
    public Group getGroupById(long groupId){

        String hql = "FROM Group where groupId = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Group> query = session.createQuery(hql);
            query.setParameter("id", groupId);

            Group group = query.uniqueResult();
            if (group != null) {
                logger.debug(group.toString());
            }
            return group;
        }
    }

    @Override
    public Group getGroupWithEvent(long groupId){

        String hql = "FROM Group as g left join fetch g.events where g.groupId = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Group> query = session.createQuery(hql);
            query.setParameter("id", groupId);

            Group group = query.uniqueResult();
            if (group != null) {
                logger.debug(group.toString());
            }
            return group;
        }
    }

    @Override
    public List<Group> getAllGroups(){
        String hql = "FROM Group ";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Group> query = session.createQuery(hql);
            return query.list();
        }

    }


    @Override
    public List<Event> getHistory(Group group){
        String hql = "FROM Event as e left join fetch e.group where e.group.groupId = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Event> query = session.createQuery(hql);
            query.setParameter("id", group.getGroupId());
            return query.list();

        }

    }

    @Override
    public Set<User> getUsers(Group group){
        String hql = "SELECT u FROM User u left JOIN fetch u.joinGroups g where g.groupId = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("id", group.getGroupId());
            Set<User> set = new HashSet<>();
            for(User u : query.list()){
                set.add(u);
            }
            return set;
        }
    }


}
