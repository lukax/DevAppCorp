/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devappcorp.controllers;

import br.uff.ic.devappcorp.services.ProfessorService;
import br.uff.ic.devappcorp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequestMapping(value = "professors", produces = "application/json;charset=UTF-8")
public class ProfessorController {
      private final ProfessorService professorService;
      private final StudentService studentService;
    @Autowired
    public ProfessorController(ProfessorService professorService,StudentService studentService){
        this.professorService = professorService;
        this.studentService = studentService;
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("professors", professorService.findAll());
        return "professors";
    }
    
    
    @RequestMapping(value = "/{taxNumber}", method = RequestMethod.GET)
    public String findOne(@PathVariable String taxNumber, Model model) {
        model.addAttribute("professor", professorService.findOneByTaxNumber(taxNumber));
        return "professor";
    }
    
     @RequestMapping("/associate/{taxNumber}")
    public String associate(@PathVariable String taxNumber, Model model) {
        model.addAttribute("professor", professorService.findOneByTaxNumber(taxNumber));
        model.addAttribute("students", studentService.findAll());
        return "associate";
    }
    
    @RequestMapping(value = "/choose/{studentTaxNumber}", params = {"professorTaxNumber"})
    public String choose(@PathVariable String studentTaxNumber,
                         @RequestParam String professorTaxNumber,
                         Model model) {
        
        studentService.createRequest(studentTaxNumber, professorTaxNumber);
                        
        return "redirect:/professors";
    }
}
