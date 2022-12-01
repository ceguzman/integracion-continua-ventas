package co.edu.poli.ventas.domain;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Table(name = "app_user", uniqueConstraints = {
        @UniqueConstraint(name = "uk_login", columnNames = {"login"}),
        @UniqueConstraint(name = "uk_email", columnNames = {"email"})
})
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 56L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(length = 50, name = "login")
    private String login;
    @NotNull
    @Column(length = 60)
    private String passwordHash;
    @Column(length = 191, name = "email")
    private String email;
    @NotNull
    private Integer activated;

    @JoinTable(name = "user_authority", joinColumns = {
            @JoinColumn(name = "id_user", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_id"))},
            inverseJoinColumns = {
                    @JoinColumn(name = "name_rol", referencedColumnName = "name", foreignKey = @ForeignKey(name = "fk_authority"))})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Authority> authorityList;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Customer customer;

    public User() {
    }

    public User(Integer id, String login, String passwordHash, String email, Integer activated) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.email = email;
        this.activated = activated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Set<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(Set<Authority> authorityList) {
        this.authorityList = authorityList;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
