package co.edu.poli.ventas.web.rest;


import co.edu.poli.ventas.domain.Authority;
import co.edu.poli.ventas.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorityResource {
    @Autowired
    AuthorityRepository authorityRepository;

    /**
     * get all Authority
     * @return list the Authority
     */
    @GetMapping("/authority")
    public ResponseEntity<List<Authority>> findAll() {
        List<Authority> authorityList = (List<Authority>) authorityRepository.findAll();
        return ResponseEntity.ok().body(authorityList);
    }

    /**
     *
     * @param name
     * @return
     */
    @GetMapping("/authority/Idauthority/{name}")
    public ResponseEntity<Authority> findById(@PathVariable String name) {
        Optional<Authority> authority = authorityRepository.findById(name);
        return ResponseEntity.ok().body(authority.get());
    }

    @GetMapping("/authority/name-like")
    public ResponseEntity<List<Authority>> findByIdLike(@RequestParam(name = "like") String like) {
        List<Authority> query = authorityRepository.findByNameLike(like);
        return ResponseEntity.ok().body(query);
    }

    @PostMapping("/authority")
    public ResponseEntity<Authority> insert(@RequestBody Authority authority) throws URISyntaxException {
        Authority result = authorityRepository.save(authority);
        return ResponseEntity.created(new URI("/api/authority" + result.getName()))
                .body(result);
    }

    @PutMapping("/authority")
    public ResponseEntity<Authority> updateAuthority(@RequestBody Authority authority) {
        Authority result = authorityRepository.save(authority);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/authority/delete/{id}")
    public ResponseEntity<Authority> deleteById(@PathVariable String id) {
        authorityRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/authority/count")
    public ResponseEntity findCount() {
        Long authority = authorityRepository.count();
        return ResponseEntity.ok().body(authority);
    }

    @DeleteMapping("/authority/deleteAll")
    public ResponseEntity<Authority> deleteAll() {
        List<Authority> query = (List<Authority>) authorityRepository.findAll();
        authorityRepository.deleteAll(query);
        return ResponseEntity.ok().build();
    }
}

