package application.controllers;

import application.DTO.UsersDto;
import application.repositories.UserRepository;
import application.security.details.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UsersDto> getUsers(){
        return UsersDto.getUsersList(userRepository.findAll());
    }
    @GetMapping("/users/{user-id}")
    private Optional<UsersDto> getOnByid(@PathVariable("user-id") Long userId){
        return UsersDto.getUserDto(userRepository.findOneById(userId));
    }
    @GetMapping("/users?age={age}")
    public List<UsersDto> getUserWhomAges(@PathVariable("age") Integer age){
      return UsersDto.getUsersList(userRepository.findUsersByAge(age));
    }
}
