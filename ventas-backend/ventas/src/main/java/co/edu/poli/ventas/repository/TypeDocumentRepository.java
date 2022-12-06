package co.edu.poli.ventas.repository;

import co.edu.poli.ventas.domain.TypeDocument;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Scope("singleton")
public interface TypeDocumentRepository extends CrudRepository<TypeDocument, Integer> {
    TypeDocument findByNameDocument(String nameDocument);

    List<TypeDocument> findByInitialsLike(String like);

    TypeDocument findByInitialsAndNameDocument(String initials, String nameDocument);

    @Transactional
    String deleteByNameDocument(String nameDocument);
}