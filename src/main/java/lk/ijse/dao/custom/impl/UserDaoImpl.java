package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.UserDao;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User user, Session session) {
        session.save(user);
        return true;
    }

    @Override
    public List<User> getAll(Session session) {
        String hql;
        hql = "FROM user";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        return users;
    }
}
