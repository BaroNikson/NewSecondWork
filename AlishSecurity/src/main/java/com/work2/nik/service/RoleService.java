package com.work2.nik.service;

import com.work2.nik.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();
    Role getRoleById(Long id);
    void addRole(Role role);
    void updateRole(Long id, Role updatedRole);
    void deleteRole(Long id);
    Role getRoleByName(String roleName);
}
