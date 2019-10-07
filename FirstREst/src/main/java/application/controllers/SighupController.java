package application.controllers;

import application.entities.User;
import application.forms.UserSighupForm;
import application.repositories.UserRepository;
import application.servicies.EmailService;
import application.servicies.TokenServices;
import application.servicies.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class SighupController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenServices tokenServices;
    @Autowired
    private EmailService mailService;

    @GetMapping("/sighUp")
    public ResponseEntity<Object> sigUp(@Valid UserSighupForm userSighupForm) {
        String login = userSighupForm.getLogin();
        String mailFromForm = userSighupForm.getMail();
        Optional<User> user = userRepository.findOneByLogin(login);
        if(user.isPresent()){
             String loginDB = user.get().getLogin();
            if (login.equals(loginDB)) {
                return new ResponseEntity<>(" Login already exist",HttpStatus.BAD_REQUEST);
            }
        } else {
            user = userRepository.findOneByMail(mailFromForm);
            if(user.isPresent()){
                String mailDB = user.get().getMail();
                if(mailDB.equals(mailFromForm)){
                    return new ResponseEntity<>("Mail already exist",HttpStatus.BAD_REQUEST);
                }
            }
        }
        String code = RandomStringUtils.random(40,true,true);
        String userMail = userSighupForm.getMail();
        mailService.send(userMail,
                "Activation Code",
                "Click this link to activate your " +
                        "account http://localhost:8899/"+ code);
        userService.saveFromSighup(userSighupForm,code);
        return new ResponseEntity<>("Confirm Your Mail",HttpStatus.CREATED);
    }

}
