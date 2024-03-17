package lk.ijse.service.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookDao;
import lk.ijse.dao.custom.impl.BookDaoImpl;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.service.custom.BookService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();
    @Override
    public boolean save(BookDto bookDto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            boolean save = bookDao.save(new Book(
                    bookDto.getId(),
                    bookDto.getTitle(),
                    bookDto.getGenre(),
                    bookDto.getImage(),
                    bookDto.getStatus(),
                    bookDto.getAuthor()
                    ),session);

            transaction.commit();
            return save;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<BookDto> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        List<BookDto> bookDtoList = new ArrayList<>();
        try{
            for (Book book:bookDao.getAll(session)){
                bookDtoList.add(new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getImage(),
                        book.getStatus(),
                        book.getAuthor()
                ));

            }
            transaction.commit();
            return bookDtoList;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }

    }

    @Override
    public boolean update(BookDto bookDto) {
        return bookDao.update(new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getGenre(),
                bookDto.getImage(),
                bookDto.getStatus(),
                bookDto.getAuthor()
        ));
    }

    @Override
    public boolean delete(String text) {
        return bookDao.delete(text);
    }
}
