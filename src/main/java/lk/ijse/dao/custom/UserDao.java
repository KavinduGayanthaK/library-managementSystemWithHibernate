package lk.ijse.dao.custom;

import lk.ijse.entity.User;
import org.hibernate.Session;

import java.util.List;

public interface UserDao {
    boolean save(User user);

    List<User> getAll();
    public User generateNewId();

    boolean updateUserName(String text);

    boolean updatePassword(String encryptPassword);

    boolean updateEmail(String text);
}
