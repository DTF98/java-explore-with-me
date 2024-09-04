package ru.DTF98.ewm.user.service;

import ru.DTF98.ewm.user.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getUsers(List<Long> ids, int from, int size);

    void deleteUser(long id);
}
