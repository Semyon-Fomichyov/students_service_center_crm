package com.jm.students.service;

import com.jm.students.model.User;
import com.jm.students.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl extends AbstractEntityServiceImpl<User> implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User update(User user) {

        if (user.getPassword() != userRepository.findById(user.getId()).getPassword()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.update(user);
    }

    @Override
    public User getUserByTelegramId(String telegramChatId) {
        return userRepository.getUserByTelegramId(telegramChatId);
    }
}

