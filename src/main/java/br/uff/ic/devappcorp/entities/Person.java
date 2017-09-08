package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.BaseEntity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person extends BaseEntity {
    @Column(unique = true, nullable = false)
    private Integer cpf;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    protected Person() {

    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public PersonName getName() {
        return PersonName.create(name).getResult();
    }

    public void setName(PersonName name) {
        this.name = name.getFullName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

