/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devappcorp.services;

import br.uff.ic.devappcorp.entities.Professor;
import br.uff.ic.devappcorp.entities.ProfessorDto;
import br.uff.ic.devappcorp.entities.Student;
import br.uff.ic.devappcorp.entities.StudentDto;
import br.uff.ic.devappcorp.exception.EntityNotFoundException;
import br.uff.ic.devappcorp.repositories.ProfessorRepository;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {   
    
     private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    
      public List<ProfessorDto> findAll() {
        ArrayList<Professor> professors = Lists.newArrayList(professorRepository.findAll());
        return professors
                .stream()
                .map(ProfessorDto::fromProfessor)
                .collect(Collectors.toList());
    }
    
      public ProfessorDto findOneByTaxNumber(String taxNumber){
        Optional<Professor> professorOrNothing = professorRepository.findByPersonDetailTaxNumber(taxNumber);
        if(!professorOrNothing.isPresent())
            throw new EntityNotFoundException();

        return ProfessorDto.fromProfessor(professorOrNothing.get());
    }
}
