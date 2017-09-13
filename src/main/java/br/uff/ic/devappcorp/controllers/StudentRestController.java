package br.uff.ic.devappcorp.controllers;

import br.uff.ic.devappcorp.entities.*;
import br.uff.ic.devappcorp.exception.EntityInvalidException;
import br.uff.ic.devappcorp.exception.EntityNotFoundException;
import br.uff.ic.devappcorp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import org.springframework.ui.Model;

@RestController
@RequestMapping(value = "api/v1/students", produces = "application/json;charset=UTF-8")
public class StudentRestController {
    private final StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService){
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<StudentDto> findAll(Model model) {
        return studentService.findAll();
    }

    @RequestMapping(value = "/{taxNumber}", method = RequestMethod.GET)
    public StudentDto findOne(@PathVariable String taxNumber) {
        return studentService.findOneByTaxNumber(taxNumber);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody StudentDto bindingModel) {
        String taxNumber = studentService.save(bindingModel);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(taxNumber).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/{taxNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String taxNumber) {
        studentService.deleteOneByTaxNumber(taxNumber);
    }

    @ExceptionHandler(EntityInvalidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleInvalidEntityException(EntityInvalidException ex) {
        //todo: logging
        return "An error has occurred\n" + ex.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(EntityNotFoundException ex) {
        //todo: logging
        return StringUtils.isEmpty(ex.getMessage()) ? "Not found" : ex.getMessage();
    }

}

