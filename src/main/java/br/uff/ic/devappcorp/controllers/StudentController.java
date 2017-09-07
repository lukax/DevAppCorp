package br.uff.ic.devappcorp.controllers;

import br.uff.ic.devappcorp.entities.Student;
import br.uff.ic.devappcorp.entities.StudentDto;
import br.uff.ic.devappcorp.exception.ResourceNotFoundException;
import br.uff.ic.devappcorp.repositories.StudentRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "students", produces = "application/json;charset=UTF-8")
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<StudentDto> findAll() {
        ArrayList<Student> students = Lists.newArrayList(studentRepository.findAll());
        List<StudentDto> bookDtos = students
                .stream()
                .map(b -> StudentDto.fromStudent(b))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public StudentDto findOne(@PathVariable Integer studentId) {
        Student student = studentRepository.findOne(studentId);
        if(student == null) throw new ResourceNotFoundException();

        return StudentDto.fromStudent(student);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(StudentDto bindingModel) {
        studentRepository.save(bindingModel.toStudent());
        return "Saved";
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.PUT)
    public void update(@PathVariable Integer studentId, StudentDto bindingModel) {
        Student student = studentRepository.findOne(studentId);
        if(student == null) throw new ResourceNotFoundException();

        student = bindingModel.toStudent();
        student.setCpf(studentId);

        studentRepository.save(student);
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer studentId) {
        Student student = studentRepository.findOne(studentId);
        if(student == null) throw new ResourceNotFoundException();

        studentRepository.delete(student);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleResourceNotFoundException(ResourceNotFoundException ex)
    {
        //Log.warn("user requested a resource which didn't exist", ex);
    }

}

