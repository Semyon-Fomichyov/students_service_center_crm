package com.jm.students.repository;

import com.jm.students.model.User;

public interface UserRepository extends AbstractEntityRepository<User> {
    User findUserByEmail(String email);
    User getUserByTelegramId(String telegramChatId);
}
