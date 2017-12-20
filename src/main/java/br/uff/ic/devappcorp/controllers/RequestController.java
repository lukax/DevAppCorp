/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devappcorp.controllers;

import br.uff.ic.devappcorp.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Eduar
 */
@Component
@RequestMapping(value = "requests", produces = "application/json;charset=UTF-8")
public class RequestController {
    
    private RequestService requestService;
    
    @Autowired
    public RequestController(RequestService requestService){
        this.requestService = requestService;        
    }
    
    @RequestMapping(value = "/professor/{taxNumber}", method = RequestMethod.GET)
    public String findByProfessor(@PathVariable String taxNumber, Model model) {
        model.addAttribute("professorRequests", requestService.findListByProfessor(taxNumber));
        return "requests";
    }
    
    @RequestMapping(value = "/student/{taxNumber}", method = RequestMethod.GET)
    public String findByStudent(@PathVariable String taxNumber, Model model) {
        model.addAttribute("studentRequests", requestService.findListByStudent(taxNumber));
        return "requests";
    }
        
}
