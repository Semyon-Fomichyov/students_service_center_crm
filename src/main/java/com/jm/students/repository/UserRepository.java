package com.jm.students.repository;

import com.jm.students.model.User;

import java.util.List;

public interface UserRepository extends AbstractEntityRepository<User> {
    User findUserByEmail(String email);
    User getUserByTelegramId(String telegramChatId);
    List<User> findByIsDisabled(boolean isDisabled);
    User findUserByResetPasswordToken(String resetPasswordToken);
}
