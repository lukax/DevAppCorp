/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devappcorp.entities;

/**
 *
 * @author Eduar
 */
public class RequestDto {

    private String professorTaxNumber;
    private String studentTaxNumber;
    private String professorName;
    private String studentName;
    
    
    public static RequestDto fromRequest(Request request){
        RequestDto requestDto = new RequestDto();
        requestDto.professorTaxNumber = request.getProfessor().getPersonDetail().getTaxNumber().getValue();
        requestDto.studentTaxNumber = request.getStudent().getPersonDetail().getTaxNumber().getValue();
        requestDto.studentName = request.getStudent().getPersonDetail().getName().getValue();
        requestDto.professorName = request.getProfessor().getPersonDetail().getName().getValue();
        return requestDto;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProfessorTaxNumber() {
        return professorTaxNumber;
    }

    public void setProfessorTaxNumber(String professorTaxNumber) {
        this.professorTaxNumber = professorTaxNumber;
    }

    public String getStudentTaxNumber() {
        return studentTaxNumber;
    }

    public void setStudentTaxNumber(String studentTaxNumber) {
        this.studentTaxNumber = studentTaxNumber;
    }

}
