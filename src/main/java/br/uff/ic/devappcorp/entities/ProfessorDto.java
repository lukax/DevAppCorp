/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devappcorp.entities;

public class ProfessorDto {
 
    public String taxNumber;
    public String name;
    public String email;

    
    public static ProfessorDto fromProfessor(Professor professor){
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.taxNumber = professor.getPersonDetail().getTaxNumber().getValue();
        professorDto.name = professor.getPersonDetail().getName().getValue();
        professorDto.email = professor.getPersonDetail().getEmail().getValue();

        return professorDto;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
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
