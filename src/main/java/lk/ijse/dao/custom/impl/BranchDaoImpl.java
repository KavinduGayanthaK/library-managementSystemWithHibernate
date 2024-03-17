package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BranchDao;
import lk.ijse.entity.Branch;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BranchDaoImpl implements BranchDao {
    @Override
    public boolean save(Branch branch) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(branch);
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
    public List<Branch> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql;
            hql = "FROM branch";
            Query query = session.createQuery(hql);
            List<Branch> branches = query.list();
            transaction.commit();
            return branches;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Branch branch) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
           String hql = "UPDATE branch SET name = :NA , city = :CY, address = :AD, postalCode = :PO  WHERE id = :ID";
           session.createQuery(hql)
                   .setParameter("NA",branch.getName())
                   .setParameter("CY",branch.getCity())
                   .setParameter("AD",branch.getAddress())
                   .setParameter("PO",branch.getPostalCode())
                   .setParameter("ID",branch.getId())
                   .executeUpdate();
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
    public Branch generateNewId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = " FROM branch ORDER BY id DESC LIMIT 1";
            Query<Branch> query = session.createQuery(hql, Branch.class);
            if (query.list().isEmpty()) {
                return new Branch();
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
    public boolean delete(String branchId) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = "DELETE FROM branch WHERE id = :entityId";
            Query query = session.createQuery(hql);
            query.setParameter("entityId", branchId);
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
