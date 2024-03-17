package lk.ijse.dao.custom;

import lk.ijse.entity.Admin;
import lk.ijse.entity.User;

import java.util.List;

public interface AdminDao {
    boolean save(Admin admin);

    List<Admin> getAll();
    public Admin generateNewId();
}
