package br.uff.ic.devappcorp.controllers;

import br.uff.ic.devappcorp.entities.Student;
import br.uff.ic.devappcorp.entities.StudentDto;
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
public class StudentController {
    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<StudentDto> findAll() {
        ArrayList<Student> students = Lists.newArrayList(studentRepository.findAll());
        List<StudentDto> bookDtos = students
                .stream()
                .map(b -> StudentDto.fromStudent(b))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public StudentDto findOne(@PathVariable Integer id) {
        Student student = studentRepository.findOne(id);
        return StudentDto.fromStudent(student);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String create(StudentDto bindingModel) {
        studentRepository.save(bindingModel.toStudent());
        return "Saved";
    }

    @RequestMapping(value = "/{studentCpf}", method = RequestMethod.PUT)
    public String update(@PathVariable Integer studentCpf, StudentDto bindingModel) {
        Student student = bindingModel.toStudent();
        student.setCpf(studentCpf);

        studentRepository.save(student);
        return "Updated";
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        studentRepository.delete(id);
        return "Removed";
    }

}

