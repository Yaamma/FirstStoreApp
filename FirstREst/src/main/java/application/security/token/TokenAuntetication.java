package application.security.token;

import application.security.details.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TokenAuntetication implements Authentication { //класс понятный спрингу

    private boolean isAuthicated; //сам токен
    private UserDetails userDetails; // аунтифицирован или нет
    private String token; // сам токен

    public TokenAuntetication(String token) {
        this.token = token;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthicated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
   this.isAuthicated=b;
    }

    @Override
    public String getName() {
        return token;
    }
}
