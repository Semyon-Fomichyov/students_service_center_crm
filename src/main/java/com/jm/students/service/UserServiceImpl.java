package com.jm.students.service;

import com.jm.students.model.User;
import com.jm.students.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

        if (!user.getPassword().equals(userRepository.findById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.update(user);
    }

    @Override
    public User getUserByTelegramId(String telegramChatId) {
        return userRepository.getUserByTelegramId(telegramChatId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public String passwordGenerator() {
        String upperCaseLetters = RandomStringUtils.random(3, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(3, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(3);
        String specialChar = RandomStringUtils.random(3, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(3);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }

    @Override
    public List<User> findByIsDisabled(boolean isDisabled) {
        return userRepository.findByIsDisabled(isDisabled);
    }

    @Override
    public User findUserByResetPasswordToken(String resetPasswordToken) {
        return userRepository.findUserByResetPasswordToken(resetPasswordToken);
    }

    @Override
    public void updateResetPasswordToken(String resetPasswordToken, String email) {
        User user = userRepository.findUserByEmail(email);
        user.setResetPasswordToken(resetPasswordToken);
        userRepository.update(user);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        userRepository.update(user);
    }
}