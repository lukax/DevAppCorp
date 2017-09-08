package br.uff.ic.devappcorp.repositories.base;

import br.uff.ic.devappcorp.entities.base.BaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface BaseEntityRepository<T extends BaseEntity> extends CrudRepository<T, Long> {

}
