package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private UserRepository userRepository;

    @Autowired
    public CommandLineRunnerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(String... arg) throws Exception {
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(new Role("ROLE_ADMIN"));
        adminRoles.add(new Role("ROLE_USER"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(new Role("ROLE_USER"));

        // пользователь Admin
        User userAdmin = new User();
        userAdmin.setName("Денис");
        userAdmin.setLast_name("Леонтьев");
        userAdmin.setUsername("l1@mail.ru");
        userAdmin.setPassword("$2y$10$08fime4hWZ5TMO.JkPEmXuIwyBchRDIbR/5QqtOnDtXE1s1LV52De");
        userAdmin.setRoles(adminRoles);
        System.out.println(userAdmin);
        userRepository.save(userAdmin);

        // пользователь User
        User userUser = new User();
        userUser.setName("Ян");
        userUser.setLast_name("Леонтьев");
        userUser.setUsername("l2@mail.ru");
        userUser.setPassword("$2y$10$GuP0CFLp71MpXFxHluKAy.t391.yfdkrTdSp6XRjvv2tnzGsTeH8O");
        userUser.setRoles(userRoles);
        userRepository.save(userUser);

    }

}
