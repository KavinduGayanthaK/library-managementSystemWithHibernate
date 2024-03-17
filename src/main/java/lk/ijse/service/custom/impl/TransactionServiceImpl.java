package lk.ijse.service.custom.impl;

import lk.ijse.controller.LoginFormController;
import lk.ijse.dao.custom.TransactionDao;
import lk.ijse.dao.custom.impl.TransactionDaoImpl;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.TransactionDto;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowTransaction;
import lk.ijse.entity.User;
import lk.ijse.service.custom.BookService;
import lk.ijse.service.custom.TransactionService;
import lk.ijse.service.custom.UserService;
import lk.ijse.tm.AdminTransactionTm;
import lk.ijse.tm.TransactionTm;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    TransactionDao transactionDao = new TransactionDaoImpl();
    BookService bookService = new BookServiceImpl();
    UserService userService = new UserServiceImpl();
    @Override
    public String generateNewTransactionId() {
        BorrowTransaction borrowTransaction = transactionDao.generateNewId();
        if(!(borrowTransaction.getId()==null)) {
            return splitId(borrowTransaction.getId());
        }
        return splitId(null);

    }

    @Override
    public boolean save(TransactionDto transactionDto) {
        Book book = null;
        for (BookDto bookDto : bookService.getAll()) {
            if (bookDto.getId() == transactionDto.getBookID()){
                book = new Book(bookDto.getId(), bookDto.getTitle(), bookDto.getGenre(), bookDto.getImage(), bookDto.getStatus(), bookDto.getAuthor());
            }
        }

        User user = null;
        for (UserDto userDto : userService.getAll1()) {
            if (userDto.getId().equals(transactionDto.getUserid())){
                user = new User(userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
            }
        }

        return transactionDao.save(new BorrowTransaction(
                transactionDto.getId(),user,book,transactionDto.getDueDate(),transactionDto.getReturnDate(),transactionDto.getStatus()
        ));

    }

    @Override
    public List<TransactionTm> getAll() {
        List<TransactionTm> transactionDtos = new ArrayList<>();
        TransactionTm transactionTm = new TransactionTm();
        for (BorrowTransaction borrowTransaction : transactionDao.getAll()){
            if (borrowTransaction.getUser().getId().equals(LoginFormController.user.getId())){
                String dueDate = String.valueOf(borrowTransaction.getDueDate());
                String returnDate = String.valueOf(borrowTransaction.getReturnDate());
                transactionDtos.add(new TransactionTm(
                        borrowTransaction.getBook().getId(),
                        borrowTransaction.getBook().getTitle(),
                        borrowTransaction.getBook().getGenre(),
                        dueDate,
                        returnDate,
                        borrowTransaction.getStatus(),
                        borrowTransaction.getUser().getId()
                ));

            }
        }
        transactionDtos.add(transactionTm);
        return transactionDtos;
    }
    @Override
    public List<AdminTransactionTm> getAll1() {
        List<AdminTransactionTm> transactionDtos = new ArrayList<>();
        for (BorrowTransaction borrowTransaction : transactionDao.getAll()){
                String dueDate = String.valueOf(borrowTransaction.getDueDate());
                String returnDate = String.valueOf(borrowTransaction.getReturnDate());
                transactionDtos.add(new AdminTransactionTm(
                        borrowTransaction.getUser().getId(),
                        borrowTransaction.getUser().getUsername(),
                        borrowTransaction.getBook().getId(),
                        borrowTransaction.getBook().getTitle(),
                        borrowTransaction.getBook().getGenre(),
                        dueDate,
                        returnDate,
                        borrowTransaction.getStatus()
                ));
            }
        return transactionDtos;
    }
    @Override
    public boolean update(long id) {
        return transactionDao.update(id);
    }

    public String splitId(String borrowTransaction ) {
        if (borrowTransaction != null) {
            String[] split = borrowTransaction.split("T");
            int id = Integer.parseInt(split[1]);

            id++;
            if (id < 10) {
                return "T00" + id;
            } else {
                return "T0" + id;
            }
        } else {
            return "T001";
        }
    }
}
