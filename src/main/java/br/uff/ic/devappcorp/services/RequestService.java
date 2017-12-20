/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devappcorp.services;

import br.uff.ic.devappcorp.entities.Professor;
import br.uff.ic.devappcorp.entities.Request;
import br.uff.ic.devappcorp.entities.RequestDto;
import br.uff.ic.devappcorp.entities.Student;
import br.uff.ic.devappcorp.entities.StudentDto;
import br.uff.ic.devappcorp.exception.EntityNotFoundException;
import br.uff.ic.devappcorp.repositories.ProfessorRepository;
import br.uff.ic.devappcorp.repositories.RequestRepository;
import br.uff.ic.devappcorp.repositories.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduar
 */
@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    
    @Autowired
    public RequestService(RequestRepository requestRepository,StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.requestRepository = requestRepository;
         this.studentRepository = studentRepository;
        this.professorRepository= professorRepository;
    }
    
    public List<RequestDto> findListByProfessor(String taxNumber){
        
        Optional<Professor> professorOrNothing = professorRepository.findByPersonDetailTaxNumber(taxNumber);
         if(!professorOrNothing.isPresent())
            throw new EntityNotFoundException();
                 
        List<Request> requestList = requestRepository.findByProfessor(professorOrNothing.get().getPersonDetail().getTaxNumber().getValue());
        
        List<RequestDto> list = new ArrayList<RequestDto>();
        for (Request request : requestList) {
              list.add(new RequestDto().fromRequest(request));               
        }               
        return list;
    }
    
     public List<RequestDto> findListByStudent(String taxNumber){
        
        Optional<Student> studentOrNothing = studentRepository.findByPersonDetailTaxNumber(taxNumber);
         if(!studentOrNothing.isPresent())
            throw new EntityNotFoundException();
                 
        List<Request> requestList = requestRepository.findByStudent(studentOrNothing.get().getPersonDetail().getTaxNumber().getValue());
        
        List<RequestDto> list = new ArrayList<RequestDto>();
        for (Request request : requestList) {
              list.add(new RequestDto().fromRequest(request));               
        }               
        return list;
    }
     
    public void delete(String studentTaxNumber, String professorTaxNumber){               
        Request req = requestRepository.findByStudentAndProfessor(studentTaxNumber, professorTaxNumber);
        requestRepository.delete(req);
    }

}
