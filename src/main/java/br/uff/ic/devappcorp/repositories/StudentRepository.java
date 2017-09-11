package br.uff.ic.devappcorp.repositories;

import br.uff.ic.devappcorp.entities.Student;
import br.uff.ic.devappcorp.repositories.base.BaseEntityRepository;

import java.util.Optional;

public interface StudentRepository extends BaseEntityRepository<Student> {

    Optional<Student> findByPersonDetailTaxNumber(String taxNumber);
}
