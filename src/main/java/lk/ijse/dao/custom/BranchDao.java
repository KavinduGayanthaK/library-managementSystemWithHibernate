package lk.ijse.dao.custom;

import lk.ijse.entity.Branch;

import java.util.List;

public interface BranchDao {
    boolean save(Branch branch);

    List<Branch> getAll();

    boolean update(Branch branch);
    Branch generateNewId();

    boolean delete(String branchId);
}
