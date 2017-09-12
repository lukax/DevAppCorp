/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devappcorp.controllers;

import br.uff.ic.devappcorp.entities.StudentDto;
import br.uff.ic.devappcorp.exception.EntityInvalidException;
import br.uff.ic.devappcorp.exception.EntityNotFoundException;
import br.uff.ic.devappcorp.services.StudentService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Eduar
 */
@Component
@RequestMapping(value = "students", produces = "application/json;charset=UTF-8")
public class StudentController {
      private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    @RequestMapping(value = "/{taxNumber}", method = RequestMethod.GET)
    public String findOne(@PathVariable String taxNumber, Model model) {
        model.addAttribute("student", studentService.findOneByTaxNumber(taxNumber));
        return "student";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestBody StudentDto bindingModel) {
        String taxNumber = studentService.save(bindingModel);      
        return "students";
    }

    @RequestMapping(value = "/delete/{taxNumber}", method = RequestMethod.GET)
    public String delete(@PathVariable String taxNumber) {
        studentService.deleteOneByTaxNumber(taxNumber);
        return "redirect:/students";
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

