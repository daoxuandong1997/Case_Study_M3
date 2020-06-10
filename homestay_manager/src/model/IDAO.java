package model;

import java.util.List;

public interface IDAO<T> {
    List<T> getAllList();

    void save(T t);

    void deleteByid(int id);

    void update(int id, T t);

    T findById(int id);
}
