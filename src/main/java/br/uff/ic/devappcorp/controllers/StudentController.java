/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devappcorp.controllers;

import br.uff.ic.devappcorp.entities.StudentDto;
import br.uff.ic.devappcorp.exception.EntityInvalidException;
import br.uff.ic.devappcorp.exception.EntityNotFoundException;
import br.uff.ic.devappcorp.services.ProfessorService;
import br.uff.ic.devappcorp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping(value = "students", produces = "application/json;charset=UTF-8")
public class StudentController {
      private final StudentService studentService;
      private final ProfessorService professorService;
    @Autowired
    public StudentController(StudentService studentService,ProfessorService professorService){
        this.studentService = studentService;
        this.professorService=professorService;
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

    @RequestMapping("/new")
    public String insert(Model model) {
        model.addAttribute("student", new StudentDto());
        return "insert";
    }
    
    @RequestMapping("/associate/{taxNumber}")
    public String associate(@PathVariable String taxNumber, Model model) {
        model.addAttribute("student", studentService.findOneByTaxNumber(taxNumber));
        model.addAttribute("professors", professorService.findAll());
        return "associate";
    }
    
    @RequestMapping(value = "/choose/{studentTaxNumber}", params = {"professorTaxNumber"})
    public String choose(@PathVariable String studentTaxNumber,
                         @RequestParam String professorTaxNumber,
                         Model model) {
        
        studentService.createRequest(studentTaxNumber, professorTaxNumber);
        
       // model.addAttribute("student", studentService.findOneByTaxNumber(studentTaxNumber));
        
        return "redirect:/students";
    }
    
      
    @RequestMapping(value = "/acceptProfessor/{studentTaxNumber}", params = {"professorTaxNumber"})
    public String acceptProfessor(@PathVariable String studentTaxNumber,
                         @RequestParam String professorTaxNumber,
                         Model model) {
        
        studentService.associate(studentTaxNumber, professorTaxNumber);
        
        //model.addAttribute("student", studentService.findOneByTaxNumber(studentTaxNumber));
        
        return "redirect:/requests/professor/"+professorTaxNumber;
    }
    
    @RequestMapping(value = "/acceptStudent/{studentTaxNumber}", params = {"professorTaxNumber"})
    public String acceptStudent(@PathVariable String studentTaxNumber,
                         @RequestParam String professorTaxNumber,
                         Model model) {
        
        studentService.associate(studentTaxNumber, professorTaxNumber);
        
        model.addAttribute("student", studentService.findOneByTaxNumber(studentTaxNumber));
        
        return "student";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(StudentDto student) {
        String taxNumber = studentService.save(student);      
        return "redirect:/students";
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

