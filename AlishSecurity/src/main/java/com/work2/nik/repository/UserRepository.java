package com.work2.nik.repository;

import com.work2.nik.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    // Дополнительные методы для работы с сущностью User
}