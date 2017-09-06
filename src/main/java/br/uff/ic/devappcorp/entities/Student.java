package br.uff.ic.devappcorp.entities;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;


public class Student extends Person {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Professor advisor;

    @ManyToMany
    private List<Classroom> classrooms;
}

