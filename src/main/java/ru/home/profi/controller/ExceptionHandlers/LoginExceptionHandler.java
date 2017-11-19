package ru.home.profi.controller.ExceptionHandlers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.home.profi.entity.Profile;
import ru.home.profi.exception.IncorrectUsernameOrPasswordException;

@ControllerAdvice
public class LoginExceptionHandler {

    @ExceptionHandler(IncorrectUsernameOrPasswordException.class)
    public String incorrectPassword(Model model) {
        model.addAttribute("error", true);
        model.addAttribute("profile", new Profile());
        return "loginForm";
    }
}
