package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.AdminDao;
import lk.ijse.entity.Admin;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(Admin admin) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(admin);
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
    public List<Admin> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql;
            hql = "FROM admin";
            Query query = session.createQuery(hql);
            List<Admin> admins = query.list();
            transaction.commit();
            return admins;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Admin generateNewId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = " FROM admin ORDER BY id DESC LIMIT 1";
            Query<Admin> query = session.createQuery(hql, Admin.class);
            if (query.list().isEmpty()) {
                return new Admin();
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
}
