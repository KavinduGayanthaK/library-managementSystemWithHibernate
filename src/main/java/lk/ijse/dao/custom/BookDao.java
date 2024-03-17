package lk.ijse.dao.custom;

import lk.ijse.entity.Book;
import org.hibernate.Session;

import java.util.List;

public interface BookDao {
    boolean save(Book book, Session session);

    List<Book> getAll(Session session);

    boolean update(Book book);

    boolean delete(String text);
}
