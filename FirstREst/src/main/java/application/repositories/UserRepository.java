package application.repositories;

import application.entities.User;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findOneByLogin(String login);
    Optional<User> findOneByMail(String mail);
    Optional<User> findOneById(Long id);
    List<User> findUsersByAge(Integer age);
    Optional<User> findOneByActivationCode(String code);
}
