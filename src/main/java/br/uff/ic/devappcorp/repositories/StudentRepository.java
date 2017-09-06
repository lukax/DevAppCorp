package br.uff.ic.devappcorp.repositories;

import br.uff.ic.devappcorp.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
