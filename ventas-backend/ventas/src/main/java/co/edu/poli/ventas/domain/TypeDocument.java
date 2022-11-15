package co.edu.poli.ventas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uk_initials", columnNames = {"initials"}),
        @UniqueConstraint(name = "uk_name_document", columnNames = {"name_document"})
})
@Entity
public class TypeDocument implements Serializable {
    private static final long serialVersionUID = 56L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(length = 20, name = "initials")
    private String initials;
    @NotNull
    @Column(length = 60, name = "name_document")
    private String nameDocument;
    @NotNull
    @Column(length = 20)
    private String stateTypeDocument;

    @JsonIgnore
    @OneToMany(mappedBy = "typeDocument")
    private List<Customer> customerList;

    public TypeDocument(@NotNull String initials, @NotNull String nameDocument, @NotNull String stateTypeDocument) {
        this.initials = initials;
        this.nameDocument = nameDocument;
        this.stateTypeDocument = stateTypeDocument;
    }

    public TypeDocument() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getNameDocument() {
        return nameDocument;
    }

    public void setNameDocument(String nameDocument) {
        this.nameDocument = nameDocument;
    }

    public String getStateTypeDocument() {
        return stateTypeDocument;
    }

    public void setStateTypeDocument(String stateTypeDocument) {
        this.stateTypeDocument = stateTypeDocument;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDocument that = (TypeDocument) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}