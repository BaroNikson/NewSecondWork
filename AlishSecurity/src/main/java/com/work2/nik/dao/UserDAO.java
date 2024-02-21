package com.work2.nik.dao;



import com.work2.nik.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(int id, User updatedUser);
    void deleteUser(int id);
    User findByUsername(String username);
}
