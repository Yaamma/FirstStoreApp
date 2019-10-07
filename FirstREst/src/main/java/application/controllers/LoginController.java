package application.controllers;

import application.DTO.TokenDto;
import application.entities.Token;
import application.entities.User;
import application.forms.LoginForm;
import application.repositories.UserRepository;
import application.servicies.TokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenServices tokenServices;

          @GetMapping("/login")
    public ResponseEntity<Object> login(LoginForm loginForm){
              String password = loginForm.getPassword();
              Optional<User> user = userRepository.findOneByLogin(loginForm.getLogin());
              if(user.isPresent()){
                  String passwordDB = user.get().getPassword();
                  if(passwordEncoder.matches(password,passwordDB)){
                      String login = loginForm.getLogin();
                      return new ResponseEntity<>(tokenServices.login(login), HttpStatus.OK);
                  }
              }
      return new ResponseEntity<>("Bad login or password",HttpStatus.BAD_REQUEST);
          }

}
