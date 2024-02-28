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
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean save = userDao.save(new User(
                    userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword()) , session);
            transaction.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<UserDto> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<UserDto> userDtos = new ArrayList<>();
            for (User user: userDao.getAll(session)){
                userDtos.add(new UserDto(
                         user.getUsername(), user.getPassword()
                ));
                transaction.commit();

            }

            return userDtos;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }
}
