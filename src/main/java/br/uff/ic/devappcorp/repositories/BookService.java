package br.uff.ic.devappcorp.repositories;

import br.uff.ic.devappcorp.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookService extends CrudRepository<Book, Long> {

}
