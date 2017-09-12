package br.uff.ic.devappcorp.entities;

public class StudentDto {

  


    public String taxNumber;
    public String name;
    public String email;

    public static StudentDto fromStudent(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.taxNumber = student.getPersonDetail().getTaxNumber().getValue();
        studentDto.name = student.getPersonDetail().getName().getValue();
        studentDto.email = student.getPersonDetail().getEmail().getValue();

        return studentDto;
    }

    
        public String getTaxNumber() {
        return taxNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

      public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

