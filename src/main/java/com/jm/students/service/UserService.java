package com.jm.students.service;

import com.jm.students.model.User;

import java.util.List;

public interface UserService extends AbstractEntityService<User> {
    String passwordGenerator();
    User findUserByEmail(String email);
    User getUserByTelegramId(String telegramChatId);
    List<User> findByIsDisabled(boolean isDisabled);
    User findUserByResetPasswordToken(String resetPasswordToken);
    void updateResetPasswordToken(String resetPasswordToken, String email);
    void updatePassword(User user, String newPassword);
}
