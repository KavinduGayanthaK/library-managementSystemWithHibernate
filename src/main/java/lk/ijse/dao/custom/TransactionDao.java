package lk.ijse.dao.custom;

import lk.ijse.entity.BorrowTransaction;

import java.util.List;

public interface TransactionDao {
    BorrowTransaction generateNewId();

    boolean save(BorrowTransaction borrowTransaction);

    List<BorrowTransaction> getAll();

    boolean update(long id);
}
