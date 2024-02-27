package com.work2.nik.service;


import com.work2.nik.dao.RoleDAO;
import com.work2.nik.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    @Override
    @Transactional
    public void updateRole(Long id, Role updatedRole) {
        Role existingRole = getRoleById(id);
        if (existingRole != null) {
            existingRole.setName(updatedRole.getName());
            // Дополнительные поля для обновления, если такие есть
        } else {
            throw new RuntimeException("Role not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String roleName) {
        return roleDAO.getRoleByName(roleName);
    }


}
