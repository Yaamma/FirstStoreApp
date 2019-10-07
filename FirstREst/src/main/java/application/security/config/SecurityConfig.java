package application.security.config;

import application.security.filters.TokenAuthFilter;
import application.security.provider.TokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationProvider authenticationProvider; //в нем токен
    @Autowired
    private TokenAuthFilter tokenAuthFilter; // проверяет токен
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http.addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .authorizeRequests().antMatchers("/login","/sighup","/").
             permitAll().anyRequest().authenticated();
    }
}
