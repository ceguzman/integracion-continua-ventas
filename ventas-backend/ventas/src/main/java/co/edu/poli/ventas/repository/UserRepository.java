package co.edu.poli.ventas.repository;

import co.edu.poli.ventas.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Scope("singleton")
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);

    List<User> findByLoginLike(String like);

    User findByLoginAndEmail(String login, String email);

    @Transactional
    String deleteByLogin(String login);
}