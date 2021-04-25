package com.jm.students.service;

import com.jm.students.model.User;

public interface UserService extends AbstractEntityService<User> {
    User getUserByTelegramId(String telegramChatId);
}
