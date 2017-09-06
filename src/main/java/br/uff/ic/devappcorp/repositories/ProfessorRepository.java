package br.uff.ic.devappcorp.repositories;

import br.uff.ic.devappcorp.entities.Professor;
import br.uff.ic.devappcorp.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}
