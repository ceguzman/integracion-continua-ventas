package co.edu.poli.ventas.web.rest;

import co.edu.poli.ventas.domain.Customer;
import co.edu.poli.ventas.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerResource {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * {@code GET  /customers} : get all the customers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customers in body.
     */
    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> findAll(){
        List<Customer> query =(List<Customer>) customerRepository.findAll();
        return  ResponseEntity.ok().body(query);
    }

    /**
     * {@code GET  /customers/:id} : get the "documentNumber" customer.
     *
     * @param documentNumber the id of the customer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer/DocumentNumber/{documentNumber}")
    public ResponseEntity<Customer> findByDocumentNumber(@PathVariable String documentNumber) {
        Customer query = customerRepository.findByDocumentNumber(documentNumber);
        return ResponseEntity.ok().body(query);
    }

    /**
     * Find by like
     * @param like the id of the customer
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer/number-Like")
    public ResponseEntity<List<Customer>> findByDocumentNumberLike(@RequestParam(name = "like") String like) {
        List<Customer> query =customerRepository.findByDocumentNumberLike(like);
        return ResponseEntity.ok().body(query);
    }

    @GetMapping("/customer/count")
    public ResponseEntity getFindCount(){
        Long query = customerRepository.count();
        return ResponseEntity.ok().body(query);
    }

    /**
     * {@code POST  /customers} : Create a new customer.
     *
     * @param customer the customerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerDTO, or with status {@code 400 (Bad Request)} if the customer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws URISyntaxException {
        if (customer.getId() != null) {
            return ResponseEntity.badRequest().build();
        }else {
            Customer result = customerRepository.save(customer);
            return ResponseEntity.created(new URI("/api/customer" + result.getId()))
                    .body(result);
        }
    }

    /**
     * {@code PUT  /customers} : Updates an existing customer.
     *
     * @param customer the customerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerDTO,
     * or with status {@code 400 (Bad Request)} if the customerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        if (customer.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Customer result = customerRepository.save(customer);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code DELETE  /customers/:id} : delete the "id" customer.
     *
     * @param documentNumber the id of the customer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer/{documentNumber}")
    public ResponseEntity<Customer> deleteByDocumentNumber(@PathVariable String documentNumber) {
        customerRepository.deleteByDocumentNumber(documentNumber);
        return ResponseEntity.ok().build();
    }

    /**
     * {@code DELETE  /customers} : delete all the customer.
     *
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer/deleteAll")
    public ResponseEntity<List<Customer>>deleteAll(){
        List<Customer> query =(List<Customer>) customerRepository.findAll();
        customerRepository.deleteAll(query);
        return  ResponseEntity.ok().build();
    }
}