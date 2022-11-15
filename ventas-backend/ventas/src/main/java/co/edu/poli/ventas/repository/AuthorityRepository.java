package co.edu.poli.ventas.repository;

import co.edu.poli.ventas.domain.Authority;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Scope("singleton")
public interface AuthorityRepository extends CrudRepository<Authority, String> {

    List<Authority> findByNameLike(String name);
}
