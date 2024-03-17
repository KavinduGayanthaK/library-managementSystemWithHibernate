package lk.ijse.service.custom;

import lk.ijse.dto.AdminDto;
import lk.ijse.dto.UserDto;

import java.util.List;

public interface AdminService {
    boolean save(AdminDto adminDto);
    List<AdminDto> getAll();
    String generateNewAdminId();
    List<AdminDto> getAll1();
}
