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

    /**
     * {@code GET /users} : get all users.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/user")
    public ResponseEntity<List<User>> findAll() {
        List<User> user = (List<User>) userRepository.findAll();
        return ResponseEntity.ok().body(user);
    }

    /**
     * {@code GET /users/:login} : get the "login" user.
     *
     * @param login the login of the user to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "login" user, or with status {@code 404 (Not Found)}.
     */
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

    /**
     * {@code POST  /users}  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param user the user to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
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

    /**
     * {@code PUT /users} : Updates an existing User.
     *
     * @param user the user to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user.
     */
    @PutMapping("/user")
    public ResponseEntity<User> update(@RequestBody User user) {
        if (user.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        User result = userRepository.save(user);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code DELETE /users/:login} : delete the "login" User.
     *
     * @param login the login of the user to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
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

    /**
     * {@code DELETE /users/:login} : delete all Users.
     *
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user/deleteAll")
    public ResponseEntity<Authority> deleteAll() {
        List<User> query = (List<User>) userRepository.findAll();
        userRepository.deleteAll(query);
        return ResponseEntity.ok().build();
    }

}