package lk.ijse.service.custom;

import lk.ijse.dto.BranchDto;
import lk.ijse.tm.BranchTm;

import java.util.Arrays;
import java.util.List;

public interface BranchService {
    boolean save(BranchDto branchDto);

    List<BranchTm> getAll();

    boolean updateBranch(BranchDto branchDto);
    String generateNewBranchId();

    boolean delete(String branchId);
}
