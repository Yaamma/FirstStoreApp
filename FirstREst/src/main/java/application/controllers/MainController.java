package application.controllers;

import application.DTO.UsersDto;
import application.entities.Token;
import application.repositories.TokenRepository;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/main")
    public Optional<UsersDto> mainPage(String token){
       Optional<Token> tok = tokenRepository.findOneByToken(token);
        return UsersDto.getUserDto(userRepository.findOneByLogin(tok.get().getUserlogin()));
    }

    @Value("${upload.path}")
    private String path;
}
class Sava{
    public static void main(String[] args) {

    }
}