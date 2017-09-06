package br.uff.ic.devappcorp.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Person {
    @Id
    @GeneratedValue
    private Long cpf;
    private String name;
    private String email;
}

