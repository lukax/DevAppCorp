/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devappcorp.repositories;

import br.uff.ic.devappcorp.entities.Professor;
import br.uff.ic.devappcorp.entities.Request;
import br.uff.ic.devappcorp.entities.Student;
import br.uff.ic.devappcorp.repositories.base.BaseEntityRepository;
import java.util.List;

/**
 *
 * @author Eduar
 */
public interface RequestRepository extends BaseEntityRepository<Request> {
   
    List<Request> findByProfessor(Professor professor);
    List<Request> findByStudent(Student student);
}
  