package lk.ijse.service.custom.impl;

import lk.ijse.dao.custom.BranchDao;
import lk.ijse.dao.custom.impl.BranchDaoImpl;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Branch;
import lk.ijse.entity.User;
import lk.ijse.service.custom.BranchService;
import lk.ijse.tm.BranchTm;

import java.util.ArrayList;
import java.util.List;

public class BranchServiceImpl implements BranchService {
    BranchDao branchDao = new BranchDaoImpl();
    @Override
    public boolean save(BranchDto branchDto) {
        return branchDao.save(
                new Branch(
                        branchDto.getBranchId(),
                        branchDto.getName(),
                        branchDto.getCity(),
                        branchDto.getAddress(),
                        branchDto.getPostelCode()
                )
        );
    }

    @Override
    public List<BranchTm> getAll() {
        List<BranchTm> branchTms = new ArrayList<>();
        for (Branch branch : branchDao.getAll()){
            branchTms.add(new BranchTm(
                    branch.getId(),
                    branch.getName(),
                    branch.getCity(),
                    branch.getAddress(),
                    branch.getPostalCode()
            ));
        }
        return branchTms;
    }

    @Override
    public boolean updateBranch(BranchDto branchDto) {
        return branchDao.update(new Branch(
                branchDto.getBranchId(),
                branchDto.getName(),
                branchDto.getCity(),
                branchDto.getAddress(),
                branchDto.getPostelCode()
        ));
    }
    @Override
    public String generateNewBranchId() {
        Branch branch = branchDao.generateNewId();
        if(!(branch.getId()==null)) {
            return splitId(branch.getId());
        }
        return splitId(null);

    }

    @Override
    public boolean delete(String branchId) {
        return branchDao.delete(branchId);
    }

    public String splitId(String branchId) {
        if (branchId != null) {
            String[] split = branchId.split("B");
            int id = Integer.parseInt(split[1]);

            id++;
            if (id < 10) {
                return "B00" + id;
            } else {
                return "B0" + id;
            }
        } else {
            return "B001";
        }
    }

}
