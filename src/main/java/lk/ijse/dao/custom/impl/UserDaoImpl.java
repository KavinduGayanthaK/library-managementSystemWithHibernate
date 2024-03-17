package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.controller.LoginFormController;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User user) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(user);
            transaction.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql;
            hql = "FROM user";
            Query query = session.createQuery(hql);
            List<User> users = query.list();
            transaction.commit();
            return users;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public User generateNewId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = " FROM user ORDER BY id DESC LIMIT 1";
            Query<User> query = session.createQuery(hql, User.class);
            if (query.list().isEmpty()) {
                return new User();
            } else {
                return query.list().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateUserName(String text) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "UPDATE user SET username = :UN WHERE id = :Id";
            int rawAffected = session.createQuery(hql)
                    .setParameter("UN",text)
                    .setParameter("Id", LoginFormController.user.getId())
                    .executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updatePassword(String encryptPassword) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "UPDATE user SET password = :PW WHERE id = :Id";
            int rawAffected = session.createQuery(hql)
                    .setParameter("PW",encryptPassword)
                    .setParameter("Id", LoginFormController.user.getId())
                    .executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateEmail(String text) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "UPDATE user SET email = :EM WHERE id = :Id";
            int rawAffected = session.createQuery(hql)
                    .setParameter("EM",text)
                    .setParameter("Id", LoginFormController.user.getId())
                    .executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

}
