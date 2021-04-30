package com.jm.students.controller;

import com.jm.students.model.User;
import com.jm.students.service.UserService;
import com.jm.students.service.util.EmailService;
import com.jm.students.service.util.UtilitySiteUrl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForgotPasswordController {

    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public ForgotPasswordController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }


    @GetMapping("/forgot_password")
    public String showForgotPassword() {
        return "forgot_password";
    }

    @PostMapping("/forgot_password")
    public String forgotPassword(Model model, HttpServletRequest request) {
        String resetPasswordToken = RandomString.make(30);
        String email = request.getParameter("email");
        userService.updateResetPasswordToken(resetPasswordToken, email);
        String resetPasswordLink = UtilitySiteUrl.getSiteURL(request) + "/reset_password?resetPasswordToken=" + resetPasswordToken;
        emailService.email(email, "ссылка для сброса пароля", resetPasswordLink);
        model.addAttribute("message", "Мы отправили вам на почту ссылку для сброса пароля.");
        return "forgot_password";
    }

    @GetMapping("/reset_password")
    public String showResetPassword(@Param(value = "resetPasswordToken") String resetPasswordToken, Model model) {
        User user = userService.findUserByResetPasswordToken(resetPasswordToken);
        model.addAttribute("resetPasswordToken", resetPasswordToken);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "reset_password";
    }

    @PostMapping("/reset_password")
    public String resetPassword(HttpServletRequest request, Model model) {
        String resetPasswordToken = request.getParameter("resetPasswordToken");
        String password = request.getParameter("password");
        User user = userService.findUserByResetPasswordToken(resetPasswordToken);
        model.addAttribute("title", "Reset your password");
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        }
        return "message";
    }
}
