package br.uff.ic.devappcorp.repositories;

import br.uff.ic.devappcorp.entities.Author;
import br.uff.ic.devappcorp.entities.Book;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookBean {
    @PersistenceContext
    private EntityManager em;

    public BookBean() {}

    public Book find(String id) {
        Book book = em.find(Book.class, id); //eager fetch
        return book;
    }

    public void create() {
        List<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Eric", "Ries"));

        Book book = new Book("9780307887917", "The Lean Startup", authors, "Business & Economics");

        em.persist(book);
    }

    public void update(String id) {
        Book book = find(id);
        em.persist(book);
    }

    public void Remove(String id) {
        Book book = find(id);
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(book);
        et.commit();
    }



}
