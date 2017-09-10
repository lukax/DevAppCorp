package br.uff.ic.devappcorp.entities;

public class StudentDto {

    public Integer taxNumber;
    public String name;
    public String email;

    public static StudentDto fromStudent(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.taxNumber = student.getPersonDetail().getTaxNumber().getValue();
        studentDto.name = student.getPersonDetail().getName().getValue();
        studentDto.email = student.getPersonDetail().getEmail().getValue();

        return studentDto;
    }


}

