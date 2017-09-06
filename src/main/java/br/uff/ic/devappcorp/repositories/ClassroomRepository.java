package br.uff.ic.devappcorp.repositories;

import br.uff.ic.devappcorp.entities.Classroom;
import br.uff.ic.devappcorp.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface ClassroomRepository extends CrudRepository<Classroom, Integer> {

}
