package application.DTO;

import application.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {
    private String name;
    private int age;
    private String lastname;
    private Long id;

    public static UsersDto from(User user){
        return UsersDto.builder().age(user.getAge())
                .lastname(user.getName())
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static List<UsersDto> getUsersList(List<User> users){
     return users.stream()
             .map(UsersDto::from)
             .collect(Collectors.toList());
    }

    public static Optional<UsersDto> getUserDto(Optional<User> user){
        if (!user.isPresent()) return Optional.empty();
        User userCandidate = user.get();
        UsersDto userDto =UsersDto.builder().name(userCandidate.getName())
                .lastname(userCandidate.getLastName())
                .id(userCandidate.getId())
                .age(userCandidate.getAge()).build();
        return Optional.of(userDto);
    }
}
