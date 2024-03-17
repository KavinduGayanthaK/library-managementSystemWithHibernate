package lk.ijse.service.custom.impl;

import lk.ijse.dao.custom.AdminDao;
import lk.ijse.dao.custom.impl.AdminDaoImpl;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.Admin;
import lk.ijse.entity.User;
import lk.ijse.service.custom.AdminService;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    AdminDao adminDao = new AdminDaoImpl();
    @Override
    public boolean save(AdminDto adminDto) {
        boolean save = adminDao.save(new Admin(
                adminDto.getId(),adminDto.getName(),adminDto.getEmail(),adminDto.getPassword()
        ));
        return save;
    }

    @Override
    public List<AdminDto> getAll() {
        List<AdminDto> adminDtos = new ArrayList<>();
        for (Admin admin: adminDao.getAll()) {
            adminDtos.add(new AdminDto(
                    admin.getName(), admin.getPassword()
            ));
        }
        return adminDtos;
    }
    @Override
    public String generateNewAdminId() {
        Admin admin = adminDao.generateNewId();
        if(!(admin.getId()==null)) {
            return splitId(admin.getId());
        }
        return splitId(null);

    }
    public String splitId(String currentAdminId) {
        if (currentAdminId != null) {
            String[] split = currentAdminId.split("A");
            int id = Integer.parseInt(split[1]);

            id++;
            if (id < 10) {
                return "A00" + id;
            } else {
                return "A0" + id;
            }
        } else {
            return "A001";
        }
    }

    @Override
    public List<AdminDto> getAll1() {
        List<AdminDto> adminDtos = new ArrayList<>();
        for (Admin admin: adminDao.getAll()) {
            adminDtos.add(new AdminDto(
                    admin.getId(),admin.getName(),admin.getEmail(), admin.getPassword()
            ));
        }
        return adminDtos;
    }

}
