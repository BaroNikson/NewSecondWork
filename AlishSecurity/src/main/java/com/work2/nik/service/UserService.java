package com.work2.nik.service;

import com.work2.nik.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    void addUser(User user);

    void updateUser(int id, User updatedUser);

    void deleteUser(int id);
    void changePassword(int id, String newPassword);
}
