package br.uff.ic.devappcorp.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student extends Person {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Professor advisor;

    @ManyToMany
    private List<Classroom> classrooms;

    public Student() {
    }

    public Professor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Professor advisor) {
        this.advisor = advisor;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}

