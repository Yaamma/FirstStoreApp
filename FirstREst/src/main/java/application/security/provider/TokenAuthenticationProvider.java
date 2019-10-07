package application.security.provider;

import application.entities.Token;
import application.repositories.TokenRepository;
import application.security.details.UserDetails;
import application.security.details.UserDetailsService;
import application.security.token.TokenAuntetication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuntetication tokenAuntetication = (TokenAuntetication) authentication;
        Optional<Token> token = tokenRepository.findOneByToken(tokenAuntetication.getName());
        if(token.isPresent()){
         UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(token.get().getUserlogin());
         tokenAuntetication.setUserDetails(userDetails);
         tokenAuntetication.setAuthenticated(true);
            return tokenAuntetication;
        } else return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuntetication.class.equals(aClass);
    }
}
