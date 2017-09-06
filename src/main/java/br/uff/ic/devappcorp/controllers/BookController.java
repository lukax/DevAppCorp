package br.uff.ic.devappcorp.controllers;

import br.uff.ic.devappcorp.repositories.StudentRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {
    private StudentRepository bookService;

    @Autowired
    public BookController(StudentRepository bookService){
        this.bookService = bookService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<BookDto> findAllBooks() {
        ArrayList<Book> books = Lists.newArrayList(bookService.findAll());
        List<BookDto> bookDtos = books
                .stream()
                .map(b -> BookDto.fromBook(b))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public BookDto findBook(@PathVariable long id) {
        Book book = bookService.findOne(id);
        return BookDto.fromBook(book);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createBook(BookDto bindingModel) {
        bookService.save(bindingModel.toBook());
        return "Saved";
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
    public String updateBook(@PathVariable long id, BookDto bindingModel) {
        Book book = bindingModel.toBook();
        book.setId(id);

        bookService.save(book);
        return "Updated";
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return "Removed";
    }

}

