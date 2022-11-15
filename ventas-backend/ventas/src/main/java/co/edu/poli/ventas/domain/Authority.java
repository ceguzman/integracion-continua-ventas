package co.edu.poli.ventas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Authority implements Serializable {

    @Id
    private String name;

    /**
     * Agredando la realcion
     * de muchos a muchos con  Authority Y User
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "authorityList",fetch = FetchType.LAZY)
    private Set<User> userList;


    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(name, authority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
