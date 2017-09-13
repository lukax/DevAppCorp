package br.uff.ic.devappcorp.repositories;

import br.uff.ic.devappcorp.entities.Professor;
import br.uff.ic.devappcorp.repositories.base.BaseEntityRepository;
import java.util.Optional;

public interface ProfessorRepository extends BaseEntityRepository<Professor> {

    Optional<Professor> findByPersonDetailTaxNumber(String taxNumber);
}
