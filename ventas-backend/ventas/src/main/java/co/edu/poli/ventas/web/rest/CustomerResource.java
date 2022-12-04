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

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> findAll(){
        List<Customer> query =(List<Customer>) customerRepository.findAll();
        return  ResponseEntity.ok().body(query);
    }

    @GetMapping("/customer/DocumentNumber/{documentNumber}")
    public ResponseEntity<Customer> findByDocumentNumber(@PathVariable String documentNumber) {
        Customer query = customerRepository.findByDocumentNumber(documentNumber);
        return ResponseEntity.ok().body(query);
    }

    @GetMapping("/customer/number-Like")
    public ResponseEntity<List<Customer>> findByDocumentNumberLike(@RequestParam(name = "like") String like) {
        List<Customer> query =customerRepository.findByDocumentNumberLike(like);
        return ResponseEntity.ok().body(query);
    }

    @GetMapping("/customer/count")
    public ResponseEntity getfindCound(){
        Long query = customerRepository.count();
        return ResponseEntity.ok().body(query);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createDependency(@RequestBody Customer customer) throws URISyntaxException {
        if (customer.getId() != null) {
            return ResponseEntity.badRequest().build();
        }else {
            Customer result = customerRepository.save(customer);
            return ResponseEntity.created(new URI("/api/customer" + result.getId()))
                    .body(result);
        }
    }

    @PutMapping("/customer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        if (customer.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Customer result = customerRepository.save(customer);
        return ResponseEntity.ok()
                .body(result);
    }

    @DeleteMapping("/customer/{documentNumber}")
    public ResponseEntity<Customer> deleteByDocumentNumber(@PathVariable String documentNumber) {
        customerRepository.deleteByDocumentNumber(documentNumber);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/customer/deleteAll")
    public ResponseEntity<List<Customer>>deleteAll(){
        List<Customer> query =(List<Customer>) customerRepository.findAll();
        customerRepository.deleteAll(query);
        return  ResponseEntity.ok().build();
    }
}