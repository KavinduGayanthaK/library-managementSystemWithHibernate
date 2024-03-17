package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookDao;
import lk.ijse.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public boolean save(Book book, Session session) {
        session.save(book);
        return true;
    }

    @Override
    public List<Book> getAll(Session session) {
        String hql = "FROM book";
        Query query = session.createQuery(hql);
        List<Book> bookList = query.list();
        return bookList;
    }

    @Override
    public boolean update(Book book) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(book);
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
    public boolean delete(String text) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Book book = session.get(Book.class, text);
            if (book != null) {
                Query deleteTransactionsQuery = session.createQuery("DELETE FROM transaction WHERE book = :book");
                deleteTransactionsQuery.setParameter("book", book);
                deleteTransactionsQuery.executeUpdate();
                session.delete(book);
                transaction.commit();
                return true;
            } else {
                System.out.println("Book not found with ID: " + text);
                return false;
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

}
