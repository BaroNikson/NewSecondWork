package com.work2.nik.service;

import com.work2.nik.dao.RoleDAO;
import com.work2.nik.dao.UserDAO;
import com.work2.nik.models.Role;
import com.work2.nik.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO,RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(int id, User updatedUser) {
        if (getUserById(id) != null) {
            // Обновляем данные существующего пользователя на основе данных из updatedUser
            updatedUser.setId(id); // Устанавливаем идентификатор пользователя
            userDAO.updateUser(id, updatedUser); // Передаем обновленного пользователя в DAO для обновления в базе данных
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
    @Override
    @Transactional
    public void changePassword(int id, String newPassword) {
        User updatedUser = getUserById(id);
        if (updatedUser!= null) {
            updatedUser.setPassword(passwordEncoder.encode(newPassword));
            userDAO.updateUser(id, updatedUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }


    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<String> getAllUserLogins() {
        List<User> users = userDAO.getAllUsers();
        return users.stream()
                .map(User::getLogin) // Предположим, что у вас есть метод getLogin() в классе User, который возвращает логин пользователя
                .collect(Collectors.toList());
    }



    @Override
    public void assignRoleToUser(String username, String roleName) {
        User user = userDAO.findByUsername(username);
        Role role = roleDAO.getRoleByName(roleName);
        if (user != null && role != null) {
            user.addRole(role);
            userDAO.updateUser(user.getId(), user); // Предполагается, что у вас есть метод updateUser в UserDAO для обновления пользователя
        } else {
            // Обработка ситуации, когда пользователь или роль не найдены
        }
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        User user = userDAO.findByUsername(username);
        Role role = roleDAO.getRoleByName(roleName);
        if (user != null && role != null) {
            user.removeRole(role);
            userDAO.updateUser(user.getId(), user); // Предполагается, что у вас есть метод updateUser в UserDAO для обновления пользователя
        } else {
            // Обработка ситуации, когда пользователь или роль не найдены
        }
    }

}
