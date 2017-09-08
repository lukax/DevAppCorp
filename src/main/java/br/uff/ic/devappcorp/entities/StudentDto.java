package br.uff.ic.devappcorp.entities;

import javax.persistence.*;
import java.util.List;

public class StudentDto {

    private Integer cpf;
    private String name;
    private String email;

    public StudentDto() {
    }

    public static StudentDto fromStudent(Student student){
        StudentDto dto = new StudentDto();
        dto.cpf = student.getCpf();
        dto.name = student.getName().getFullName();
        dto.email = student.getEmail();
        return dto;
    }

    public Student toStudent(){
        Student student = new Student();
        student.setCpf(cpf);
        student.setEmail(email);
        student.setName(PersonName.create(name).getResult());
        return student;
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

