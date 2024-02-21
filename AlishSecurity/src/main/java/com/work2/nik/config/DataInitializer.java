package com.work2.nik.config;

import com.work2.nik.models.Role;
import com.work2.nik.models.User;
import com.work2.nik.repository.RoleRepository;
import com.work2.nik.repository.UserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;


@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initData() {
        // Проверяем наличие ролей в базе данных
        if (roleRepository.count() == 0) {
            Role adminRole = new Role("ADMIN");
            Role userRole = new Role("USER");
            roleRepository.saveAll(Arrays.asList(adminRole, userRole));
        }

        // Проверяем наличие пользователей в базе данных
        if (userRepository.count() == 0) {
            // Создаем пользователей и связываем их с ролями
            Role adminRole = roleRepository.findByName("ADMIN");
            Role userRole = roleRepository.findByName("USER");

            User adminUser = new User("admin", "adminPassword");
            adminUser.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
            userRepository.save(adminUser);

            User regularUser = new User("user", "userPassword");
            regularUser.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepository.save(regularUser);
        }
    }
}
