package com.work2.nik.config;

import com.work2.nik.models.Role;
import com.work2.nik.models.User;
import com.work2.nik.service.RoleService;
import com.work2.nik.service.UserService;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initData() {
        // Проверяем наличие ролей в базе данных
        if (roleService.getAllRoles().isEmpty()) {
            Role adminRole = new Role("ADMIN");
            Role userRole = new Role("USER");
            roleService.addRole(adminRole);
            roleService.addRole(userRole);
        }

        // Проверяем наличие пользователей в базе данных
        if (userService.getAllUsers().isEmpty()) {
            // Создаем пользователей и связываем их с ролями
            Role adminRole = roleService.getRoleByName("ADMIN");
            Role userRole = roleService.getRoleByName("USER");

            User adminUser = new User("admin", "adminPassword");
            adminUser.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
            userService.addUser(adminUser);

            User regularUser = new User("user", "userPassword");
            regularUser.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userService.addUser(regularUser);
        }
    }
}
