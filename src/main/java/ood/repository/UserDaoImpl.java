package ood.repository;

import ood.model.Group;
import ood.model.User;
import ood.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
            session.persist(user);
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

    @Override
    public synchronized User getUserByName(String userName) {
        if (userName == null) return null;

        String hql = "FROM User where lower(userName) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("name", userName.toLowerCase());

            User user = query.uniqueResult();
            if (user != null) {
                logger.debug(user.toString());
            }
            return user;
        }
    }

    @Override
    public User getUserById(long userId) {

        String hql = "FROM User where userId = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("id", userId);

            User user = query.uniqueResult();
            if (user != null) {
                logger.debug(user.toString());
            }
            return user;
        }
    }

    @Override
    public User getUserByEmail(String email) {

        String hql = "FROM User where lower(email) = :email";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase());

            User user = query.uniqueResult();
            if (user != null) {
                logger.debug(user.toString());
            }
            return user;
        }
    }

    @Override
    public User getUserByPhone(String phone) {

        String hql = "FROM User where phone = :phone";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("phone", phone);

            User user = query.uniqueResult();
            if (user != null) {
                logger.debug(user.toString());
            }
            return user;
        }
    }

    @Override
    public Set<Group> getOwnGroups(User user) {

        String hql = "FROM Group as g left join fetch g.moderator where g.moderator.userId = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Group> query = session.createQuery(hql);
            query.setParameter("id", user.getUserId());
            Set<Group> set = new HashSet<>();
            for(Group u : query.list()){
                set.add(u);
            }
            return set;


        }
    }

    @Override
    public synchronized Set<Group> getJoinGroups (User user){
            String hql = "SELECT g FROM Group g left JOIN fetch g.users u where u.userId = :id";

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Query<Group> query = session.createQuery(hql);
                query.setParameter("id", user.getUserId());
                Set<Group> set = new HashSet<>();
                for(Group u : query.list()){
                    set.add(u);
                }

                session.close();
                return set;
            }
        }

    @Override
    public User getUserWithGroup(long userId) {

        String hql = "FROM User u left join fetch u.ownGroups where u.userId = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("id", userId);

            User user = query.uniqueResult();
            if (user != null) {
                logger.debug(user.toString());
            }
            return user;
        }
    }

    @Override
    public User getUserByCredentials(String name, String password){
        String hql = "FROM User as u where u.userName = :name and u.password = :password";
        logger.debug(String.format("User name: %s, password: %s", name, password));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("name", name);
            query.setParameter("password", password);
            return query.uniqueResult();
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public User getUserByCredentials(long userId, String password){
        String hql = "FROM User as u where u.userId = :id and u.password = :password";
        logger.debug(String.format("User id: %s, password: %s", userId, password));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("id", userId);
            query.setParameter("password", password);
            return query.uniqueResult();
        }
        catch (Exception e){
            return null;
        }
    }

//    @Override
//    public User addOwnGroup(User user, Group group){
//        Transaction transaction = null;
//        try{
//            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            transaction = session.beginTransaction();
//
//            user.getOwnGroups().add(group);
//
//            session.update(user);
//
//            transaction.commit();
//        }catch (Exception e){
//            if(transaction != null) transaction.rollback();
//            logger.error(e.getMessage());
//        }
//        //if (group!=null) logger.debug(String.format("The group %s was inserted into the table.", group.toString()));
//        return user;
//    }



//    @Override
//    public User deleteOwnGroup(User user, Group group){
//        Transaction transaction = null;
//        try{
//            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            transaction = session.beginTransaction();
//
//            user.getJoinGroups().remove(group);
//
//            session.update(user);
//
//            transaction.commit();
//        }catch (Exception e){
//            if(transaction != null) transaction.rollback();
//            logger.error(e.getMessage());
//        }
//        //if (group!=null) logger.debug(String.format("The group %s was inserted into the table.", group.toString()));
//        return user;
//    }

    @Override
    public  User addJoinGroup(User user, Group group){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();


                Set<Group> joinGroupSet = user.getJoinGroups();
                System.out.println(joinGroupSet.size());
                joinGroupSet.add(group);
                user.setJoinGroups(joinGroupSet);
                session.update(user);





            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        //if (group!=null) logger.debug(String.format("The group %s was inserted into the table.", group.toString()));
        return user;
    }

    @Override
    public User leaveJoinGroup(User user, Group group){
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Set<Group> joinGroupSet = user.getJoinGroups();

            joinGroupSet.remove(group);
            user.setJoinGroups(joinGroupSet);


            session.update(user);

            transaction.commit();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        //if (group!=null) logger.debug(String.format("The group %s was inserted into the table.", group.toString()));
        return user;
    }

}


