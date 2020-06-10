package model.user;

import model.IDAO;
import model.user.User;

import java.util.List;

public class UserDAO implements IDAO<User> {
    @Override
    public List<User> getAllList() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void deleteByid(int id) {

    }

    @Override
    public void update(int id, User user) {

    }

    @Override
    public User findById(int id) {
        return null;
    }
}
