package application.controllers;

import application.entities.User;
import application.models.State;
import application.repositories.UserRepository;
import application.servicies.TokenServices;
import application.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ActivationController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenServices tokenServices;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{code}")
    public ResponseEntity<Object> doConfirm(@PathVariable("code") String code){
        Optional<User> user = userService.findOneByCode(code);
        if(!user.isPresent()) {return new ResponseEntity<>("Bad code", HttpStatus.BAD_REQUEST);}
        String login = user.get().getLogin();
        user.get().setState(State.ACTIVE);
        return new ResponseEntity<>(tokenServices.login(login),HttpStatus.OK);
    }


}
