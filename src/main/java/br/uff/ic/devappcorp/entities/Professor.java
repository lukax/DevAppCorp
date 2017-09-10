package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Professor extends BaseEntity {

    @OneToOne
    private PersonDetail personDetail;

    private String degree;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "advisor")
    private List<Student> students;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "professor")
    private List<Classroom> classrooms;

    protected Professor() { } // jpa only

    public Professor(PersonDetail personDetail){
        this.personDetail = personDetail;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}
