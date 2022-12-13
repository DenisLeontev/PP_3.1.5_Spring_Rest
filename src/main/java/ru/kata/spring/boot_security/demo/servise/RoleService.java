package ru.kata.spring.boot_security.demo.servise;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleOfId(Long id) {
        return roleRepository.findById(id).get();
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public List<Role> getUniqAllRoles() {
        List<ru.kata.spring.boot_security.demo.model.Role> roleList = roleRepository.findAll();
        Set<ru.kata.spring.boot_security.demo.model.Role> roleSet = new HashSet<>(roleList);
        roleList.clear();
        roleList.addAll(roleSet);
        return roleList;
    }

    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

}
