package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
              optional = false,
              orphanRemoval = true)
    private PersonDetail personDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    private Professor advisor;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Classroom> classrooms;

    protected Student() { } // jpa only

    public Student(PersonDetail personDetail) {
        this.personDetail = personDetail;
    }

    public PersonDetail getPersonDetail() {
        return personDetail;
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

