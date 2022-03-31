package ru.kata.spring.boot_security.demo.loadusers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;


@Component
public class loader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    public loader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
//        User user = new User();
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(new Role(1L, "USER"));
//
//        user.setId(1L);
//        user.setEmail("user@mail.ru");
//        user.setPassword("user");
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setName("Alex");
//        user.setSurname("Ipa");
//        user.setAge(34);
//        user.setRoles(roleSet);
//        userRepository.save(user);
//
//        user.setId(2L);
//        user.setEmail("user1@mail.ru");
//        user.setPassword("user1");
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setName("Elena");
//        user.setSurname("Hramova");
//        user.setAge(24);
//        user.setRoles(roleSet);
//        userRepository.save(user);
//
//        roleSet.clear();
//        roleSet.add(new Role(2L, "ADMIN"));
//
//        user.setId(3L);
//        user.setEmail("admin@mail.ru");
//        user.setPassword("admin");
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setName("Paul ");
//        user.setSurname("Key");
//        user.setAge(34);
//        user.setRoles(roleSet);
//        userRepository.save(user);
//
//        roleSet.add(new Role(1L, "USER"));
//
//        user.setId(4L);
//        user.setEmail("admin1@mail.ru");
//        user.setPassword("admin1");
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setName("Tamara");
//        user.setSurname("Pavlikova");
//        user.setAge(54);
//        user.setRoles(roleSet);
//        userRepository.save(user);
    }
}

