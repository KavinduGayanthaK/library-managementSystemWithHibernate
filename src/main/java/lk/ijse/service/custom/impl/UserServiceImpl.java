package lk.ijse.service.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dao.custom.impl.UserDaoImpl;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;
import lk.ijse.service.custom.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();
    @Override
    public boolean save(UserDto userDto) {
        boolean save = userDao.save(new User(
                userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword()));
        return save;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: userDao.getAll()) {
            userDtos.add(new UserDto(
                    user.getUsername(), user.getPassword()
            ));
        }
        return userDtos;
    }
    @Override
    public List<UserDto> getAll1() {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: userDao.getAll()) {
            userDtos.add(new UserDto(
                    user.getId(),user.getUsername(),user.getEmail(),user.getPassword()
            ));
        }
        return userDtos;
    }

    @Override
    public boolean update(String text) {
        return userDao.updateUserName(text);
    }

    @Override
    public boolean updatePassword(String encryptPassword) {
        return userDao.updatePassword(encryptPassword);
    }

    @Override
    public boolean updateEmail(String text) {
        return userDao.updateEmail(text);
    }

    @Override
    public String generateNewUserId() {
        User user = userDao.generateNewId();
        if(!(user.getId()==null)) {
            return splitId(user.getId());
        }
        return splitId(null);

    }

    public String splitId(String currentUserId) {
        if (currentUserId != null) {
            String[] split = currentUserId.split("M");
            int id = Integer.parseInt(split[1]);

            id++;
            if (id < 10) {
                return "M00" + id;
            } else {
                return "M0" + id;
            }
        } else {
            return "M001";
        }
    }
}
