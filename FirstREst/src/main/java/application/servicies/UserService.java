package application.servicies;

import application.entities.User;
import application.forms.UserSighupForm;
import application.models.Role;
import application.models.State;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

          public void saveFromSighup(UserSighupForm userSighupForm,String code){
        userRepository.save(
                User.builder()
                .age(userSighupForm.getAge()).
                        password(passwordEncoder.encode(userSighupForm.getPassword())).
                        login(userSighupForm.getLogin())
                        .mail(userSighupForm.getMail())
                        .activationCode(code)
                        .lastName(userSighupForm.getLastname())
                        .name(userSighupForm.getName())
                        .role(Role.USER)
                        .state(State.UNCONFIRMED)
                        .build()
    );
          }

        public Optional<User> findOneByCode(String code){
              return userRepository.findOneByActivationCode(code);
        }
}
