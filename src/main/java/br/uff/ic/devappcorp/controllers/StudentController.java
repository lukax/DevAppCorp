package br.uff.ic.devappcorp.controllers;

import br.uff.ic.devappcorp.entities.*;
import br.uff.ic.devappcorp.exception.EntityInvalidException;
import br.uff.ic.devappcorp.exception.EntityNotFoundException;
import br.uff.ic.devappcorp.repositories.StudentRepository;
import br.uff.ic.devappcorp.utils.Result;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// ("HATEOAS", "Hypermedia as the Engine of Application State") design pattern.
// Hypermedia promotes service longevity by decoupling the consumer of a service
// from intimate knowledge of that service’s surface area and topology. It describes
// REST services. The service can answer questions about what to call, and when.

@RestController
@RequestMapping(value = "students", produces = "application/json;charset=UTF-8")
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<StudentDto> findAll() {
        ArrayList<Student> students = Lists.newArrayList(studentRepository.findAll());
        return students
                .stream()
                .map(StudentDto::fromStudent)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public StudentDto findOne(@PathVariable Long studentId) {
        Student student = studentRepository.findOne(studentId);
        if(student == null) throw new EntityNotFoundException();
        return StudentDto.fromStudent(student);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(StudentDto bindingModel) {
        Result<PersonTaxNumber> taxNumber = PersonTaxNumber.create(bindingModel.taxNumber);
        Result<PersonName> name = PersonName.create(bindingModel.name);
        Result<EmailAddress> email = EmailAddress.create(bindingModel.email);

        Result result = Result.combine(taxNumber, name, email);
        if(result.isFailure()){
            throw new EntityInvalidException(result.getError());
        }

        PersonDetail personDetail = new PersonDetail(taxNumber.value(), name.value(), email.value());
        Student student = new Student(personDetail);

        studentRepository.save(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long studentId) {
        Student student = studentRepository.findOne(studentId);
        if(student == null) throw new EntityNotFoundException();
        studentRepository.delete(student);
    }


    @ExceptionHandler(EntityInvalidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleInvalidEntityException(EntityInvalidException ex) {
        //todo: logging
        return "An error has occurred. " + ex.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(EntityNotFoundException ex) {
        //todo: logging
        return StringUtils.isEmpty(ex.getMessage()) ? "Not found." : ex.getMessage();
    }

}

