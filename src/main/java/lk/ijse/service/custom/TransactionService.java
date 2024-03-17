package lk.ijse.service.custom;

import lk.ijse.dto.TransactionDto;
import lk.ijse.tm.AdminTransactionTm;
import lk.ijse.tm.TransactionTm;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    String generateNewTransactionId();

    boolean save(TransactionDto transactionDto);

    List<TransactionTm> getAll();

    boolean update(long id);
    List<AdminTransactionTm> getAll1();

}
