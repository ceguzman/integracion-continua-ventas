package co.edu.poli.ventas.web.rest;

import co.edu.poli.ventas.domain.Authority;
import co.edu.poli.ventas.domain.User;
import co.edu.poli.ventas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserResource {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> findAll() {
        List<User> user = (List<User>) userRepository.findAll();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/userlogin/{login}")
    public ResponseEntity<User> findByLogin(@PathVariable String login) {
        User user = userRepository.findByLogin("carluchin");
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/login-like")
    public ResponseEntity<List<User>> findByLoginLike(@RequestParam(name = "like") String like) {
        List<User> user = userRepository.findByLoginLike(like);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> insert(@RequestBody User user) throws URISyntaxException {
        if (user.getId() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            User result = userRepository.save(user);
            return ResponseEntity.created(new URI("/api/user" + result.getId()))
                    .body(result);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<User> update(@RequestBody User user) {
        if (user.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        User result = userRepository.save(user);
        return ResponseEntity.ok()
                .body(result);
    }

    @DeleteMapping("/user/{login}")
    public ResponseEntity<User> delete(@PathVariable String login) {
        userRepository.deleteByLogin(login);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/count")
    public ResponseEntity findCount() {
        Long user = userRepository.count();
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/user/deleteAll")
    public ResponseEntity<Authority> deleteAll() {
        List<User> query = (List<User>) userRepository.findAll();
        userRepository.deleteAll(query);
        return ResponseEntity.ok().build();
    }

}