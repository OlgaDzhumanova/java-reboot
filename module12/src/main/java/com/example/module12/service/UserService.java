package com.example.module12.service;

import com.example.module12.entity.User;
import com.example.module12.exception.ItemNotFoundException;
import com.example.module12.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new ItemNotFoundException("Car not found, id = " + id));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        findById(user.getId());
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
