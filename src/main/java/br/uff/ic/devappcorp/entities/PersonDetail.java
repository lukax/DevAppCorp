package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.BaseEntity;

import javax.persistence.*;

@Entity
public class PersonDetail extends BaseEntity {
    @Column(unique = true, nullable = false)
    private Integer taxNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    protected PersonDetail() {
    }

    public PersonDetail(PersonTaxNumber taxNumber, PersonName name, EmailAddress email)
    {
        this.taxNumber = taxNumber.getValue();
        this.name = name.getValue();
        this.email = email.getValue();
    }

    public PersonTaxNumber getTaxNumber() {
        return PersonTaxNumber.create(taxNumber).value();
    }

    public PersonName getName() {
        return PersonName.create(name).value();
    }

    public EmailAddress getEmail() {
        return EmailAddress.create(email).value();
    }

}

