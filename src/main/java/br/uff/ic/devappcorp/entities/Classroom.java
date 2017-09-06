package br.uff.ic.devappcorp.entities;

import javax.persistence.*;
import java.util.List;

public class Classroom {
    @Id
    @GeneratedValue
    private Long code;
    private int year;
    private int semester;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Student> students;

    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;
}
