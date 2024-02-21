package com.work2.nik.dao;

import com.work2.nik.models.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Long id);

}
