package ru.home.profi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.home.profi.entity.Company;
import ru.home.profi.entity.Education;
import ru.home.profi.entity.Profile;
import ru.home.profi.entity.WorkExperience;
import ru.home.profi.exception.IncorrectUsernameOrPasswordException;
import ru.home.profi.model.MessageFlag;
import ru.home.profi.service.CompanyService;
import ru.home.profi.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    /// LOGS!!!!!!

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("employee", new Profile());
        model.addAttribute("employer", new Company());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createEntity(RedirectAttributes attributes, @Valid Profile profile,
                               @Valid Company company, Errors errors) {
        if (errors.hasErrors())
            return "register";
        if (profile.getEmail() != null) {
            Profile newProfile = userService.registerProfile(profile);
            attributes.addAttribute("username", newProfile.getUsername());
            return "redirect:profile/{username}";
        }
        if (company.getCompanyEmail() != null) {
            Company newCompany = companyService.registerCompany(company);
            attributes.addAttribute("companyName", newCompany.getName());
            return "";
        }
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("profile", new Profile());
        model.addAttribute("error", false);
        return "/loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String profile(RedirectAttributes attributes, @Valid Profile profile, Errors errors) {
        if (errors.hasErrors())
            return "loginForm";
        Profile currentProfile = userService.findByEmail(profile.getEmail());
        // tests
        currentProfile.setSkills("dsfa");
        currentProfile.setLanguages("dsafhsd");

        if ((currentProfile == null) || !(profile.getPassword().equals(currentProfile.getPassword())))
            throw new IncorrectUsernameOrPasswordException();
        List<WorkExperience> workExperience = userService.findProfileWorkExperience(profile.getEmail());
        List<Education> education = userService.findProfileEducation(profile.getEmail());
        attributes.addAttribute("username", currentProfile.getUsername());
        attributes.addFlashAttribute("profile", currentProfile);
        attributes.addFlashAttribute("workExperience", workExperience);
        attributes.addFlashAttribute("education", education);
        return "redirect:/profile/{username}";
    }

    @RequestMapping(value = "profile/{username}", method = RequestMethod.GET)
    public String userProfile(@PathVariable("username") String username, Model model) {
        Profile currentProfile = null;
        if (!model.containsAttribute("profile")) {
            currentProfile = userService.findByUsername(username);
            model.addAttribute("profile", currentProfile);
        }
        if (!model.containsAttribute("workExperience")) {
            if (currentProfile == null)
                currentProfile = userService.findByUsername(username);
            model.addAttribute("workExperience", userService.findProfileWorkExperience(currentProfile.getEmail()));
        }
        if (!model.containsAttribute("education")) {
            if (currentProfile == null)
                currentProfile = userService.findByUsername(username);
            model.addAttribute("education", userService.findProfileEducation(currentProfile.getEmail()));
        }
        return "profile";
    }

    /*
    to change to profile/editProfile + html (action)
     */
    @RequestMapping(value = "{username}/editProfile", method = RequestMethod.GET)
    public String editProfile(@PathVariable("username") String username, Model model) {
        List<WorkExperience> workExperiences = new ArrayList<WorkExperience>();
        WorkExperience w1 = new WorkExperience();
        w1.setYear("2001");
        w1.setDuties("asdfuahsdifahsdiuf");
        WorkExperience w2 = new WorkExperience();
        w2.setYear("2001");
        w2.setDuties("asdfuahsdifahsdiuf");
        workExperiences.add(w1);
        workExperiences.add(w2);
        Profile profile = new Profile();

        model.addAttribute("experience", workExperiences);
        model.addAttribute("profile", profile);
        model.addAttribute("education", new Education());
        return "editProfile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String performEdition(Profile profile, WorkExperience experience, Model model) {
        userService.updateProfile(profile);
        model.addAttribute("username", profile.getUsername());
        return "loginForm";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "testPage";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String messages() {
        return "myMessage";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settings() {
        return "settings";
    }
}

