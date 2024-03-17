package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.controller.LoginFormController;
import lk.ijse.dao.custom.TransactionDao;
import lk.ijse.entity.BorrowTransaction;
import lk.ijse.entity.User;
import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public BorrowTransaction generateNewId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = " FROM transaction ORDER BY id DESC LIMIT 1";
            Query<BorrowTransaction> query = session.createQuery(hql, BorrowTransaction.class);
            if (query.list().isEmpty()) {
                return new BorrowTransaction();
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
    public boolean save(BorrowTransaction borrowTransaction) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(borrowTransaction);
            String hql = "update book set status = :Sta where id = :ID";
            int rawAffected = session.createQuery(hql)
                    .setParameter("Sta",borrowTransaction.getStatus())
                    .setParameter("ID",borrowTransaction.getBook().getId())
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
    public List<BorrowTransaction> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql;
            hql = "FROM transaction ";
            Query query = session.createQuery(hql);
            List<BorrowTransaction> borrowTransactions = query.list();
            transaction.commit();
            return borrowTransactions;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

        @Override
        public boolean update(long id) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            try {
                String hql = "UPDATE transaction SET status = :ST,returnDate = :RD WHERE user.id = :Id";
                String hql1 = "UPDATE book set status = :Sta where id = :ID";
                int rawAffected = session.createQuery(hql)
                        .setParameter("ST","Returned")
                        .setParameter("RD", LocalDate.now())
                        .setParameter("Id",LoginFormController.user.getId())
                        .executeUpdate();

                int rawAffected1 = session.createQuery(hql1)
                        .setParameter("Sta","Available")
                        .setParameter("ID",id)
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
    }

