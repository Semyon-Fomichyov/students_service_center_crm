package com.jm.students.repository;

import com.jm.students.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractEntityRepositoryImpl<User> implements UserRepository {

    @Override
    public void save(User user) {
        user.setOrganization(entityManager.merge(user.getOrganization()));
        super.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) entityManager.createQuery("select u from User u where u.email = :userEmail")
                .setParameter("userEmail", email)
                .getSingleResult();
    }

    @Override
    public User getUserByTelegramId(String telegramChatId) {
        return (User) entityManager.createQuery("select u from User u where u.telegramChatId=:telegramChatId")
                .setParameter("telegramChatId", telegramChatId)
                .getSingleResult();
    }
}
