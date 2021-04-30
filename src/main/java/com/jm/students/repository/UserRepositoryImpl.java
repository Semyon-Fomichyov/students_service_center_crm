package com.jm.students.repository;

import com.jm.students.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractEntityRepositoryImpl<User> implements UserRepository {

    @Override
    public void save(User user) {
        user.setOrganization(entityManager.merge(user.getOrganization()));
        super.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            return (User) entityManager.createQuery("select u from User u where u.email = :userEmail")
                    .setParameter("userEmail", email)
                    .getSingleResult();
        } catch (Exception e) {
            return  null;
        }
    }

    @Override
    public User getUserByTelegramId(String telegramChatId) {
        return (User) entityManager.createQuery("select u from User u where u.telegramChatId=:telegramChatId")
                .setParameter("telegramChatId", telegramChatId)
                .getSingleResult();
    }
    @SuppressWarnings("unchecked")
    public List<User> findByIsDisabled(boolean isDisabled) {
        TypedQuery<User> userTypedQuery = (TypedQuery<User>) entityManager.createQuery("select u from User u where u.isDisabled=:isDisabled")
                .setParameter("isDisabled", isDisabled);
        return userTypedQuery.getResultList();
    }

    @Override
    public User findUserByResetPasswordToken(String resetPasswordToken) {
        return (User) entityManager.createQuery("select u from User u where u.resetPasswordToken=:resetPasswordToken")
                .setParameter("resetPasswordToken", resetPasswordToken)
                .getSingleResult();
    }

}
