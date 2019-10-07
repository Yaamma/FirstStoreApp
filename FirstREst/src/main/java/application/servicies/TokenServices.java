package application.servicies;

import application.DTO.TokenDto;
import application.DTO.UsersDto;
import application.entities.Token;
import application.entities.User;
import application.repositories.TokenRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServices {

    @Autowired
    private TokenRepository tokenRepository;

    public TokenDto login(String login){
        Token token = Token.builder().userlogin(login).token(RandomStringUtils.random(30,true,true)).build();
        tokenRepository.save(token);
        return TokenDto.builder().token(token.getToken()).build();
    }
    @Autowired
    private UserService userService;

    public Optional<UsersDto> getUserByCode(String code){
        Optional<User> user = userService.findOneByCode(code);
        return UsersDto.getUserDto(user);
    }
}
