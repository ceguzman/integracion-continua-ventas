package co.edu.poli.ventas.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uk_document_number_id_type_dcoument", columnNames = {"document_number", "id_type_document"}),
        @UniqueConstraint(name = "uk_id_user_cust", columnNames = {"id_user"})
})
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 56L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(length = 20, name = "document_number")
    private String documentNumber;

    @NotNull
    @Column(length = 50)
    private String firstName;
    @NotNull
    @Column(length = 50)
    private String firstSurname;
    @Column(length = 50)
    private String secondName;
    @Column(length = 50)
    private String secondSurname;

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_user", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_id_user"))
    private User user;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "id_type_document", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_type_document"))
    private TypeDocument typeDocument;

    public Customer() {
    }

    public Customer(Integer id, String documentNumber, String firstName, String firstSurname, String secondName, String secondSurname, User user) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondName = secondName;
        this.secondSurname = secondSurname;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
