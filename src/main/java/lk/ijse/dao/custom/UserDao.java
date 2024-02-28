package lk.ijse.dao.custom;

import lk.ijse.entity.User;
import org.hibernate.Session;

import java.util.List;

public interface UserDao {
    boolean save(User user, Session session);

    List<User> getAll(Session session);
}
