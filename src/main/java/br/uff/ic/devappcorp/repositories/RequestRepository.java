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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Eduar
 */
public interface RequestRepository extends BaseEntityRepository<Request> {
   
    
    @Query("from Request req where req.professor.personDetail.taxNumber =:taxNumber")
    List<Request> findByProfessor(@Param("taxNumber")String taxNumber);
    
    @Query("from Request req where req.student.personDetail.taxNumber =:taxNumber")
    List<Request> findByStudent(@Param("taxNumber") String taxNumber);
    
    @Query("from Request req where req.student.personDetail.taxNumber =:studentTaxNumber and req.professor.personDetail.taxNumber =:professorTaxNumber")
    Request findByStudentAndProfessor(@Param("studentTaxNumber") String studentTaxNumber,@Param("professorTaxNumber") String professorTaxNumber);
}
  