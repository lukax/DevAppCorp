package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Professor extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
              optional = false,
              orphanRemoval = true)
    private PersonDetail personDetail;

    private String degree;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "advisor")
    private List<Student> students;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
    private List<Classroom> classrooms;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
    private List<Request> requests;
    
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
    
     public PersonDetail getPersonDetail() {
        return personDetail;
    }

    /**
     * @param personDetail the personDetail to set
     */
    public void setPersonDetail(PersonDetail personDetail) {
        this.personDetail = personDetail;
    }

    /**
     * @return the requests
     */
    public List<Request> getRequests() {
        return requests;
    }

    /**
     * @param requests the requests to set
     */
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
