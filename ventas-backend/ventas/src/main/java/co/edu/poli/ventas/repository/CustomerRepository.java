package co.edu.poli.ventas.repository;

import co.edu.poli.ventas.domain.Customer;
import co.edu.poli.ventas.domain.TypeDocument;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Scope("singleton")
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    public Customer findByDocumentNumber(String documentNumber);

    public Customer findByDocumentNumberAndAndTypeDocument(String documentNumber, TypeDocument typeDocument);

    public List<Customer> findByDocumentNumberLike(String search);

    public Customer findByIdEquals(Integer buscaId);

    @Transactional
    public String deleteByDocumentNumber(String delete);
}