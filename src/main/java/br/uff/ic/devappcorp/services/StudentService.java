package br.uff.ic.devappcorp.services;

import br.uff.ic.devappcorp.entities.*;
import br.uff.ic.devappcorp.exception.EntityInvalidException;
import br.uff.ic.devappcorp.exception.EntityNotFoundException;
import br.uff.ic.devappcorp.repositories.StudentRepository;
import br.uff.ic.devappcorp.utils.Result;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> findAll() {
        ArrayList<Student> students = Lists.newArrayList(studentRepository.findAll());
        return students
                .stream()
                .map(StudentDto::fromStudent)
                .collect(Collectors.toList());
    }

    public StudentDto findOneByTaxNumber(String taxNumber){
        Optional<Student> studentOrNothing = studentRepository.findByPersonDetailTaxNumber(taxNumber);
        if(!studentOrNothing.isPresent())
            throw new EntityNotFoundException();

        return StudentDto.fromStudent(studentOrNothing.get());
    }

    public String save(StudentDto studentDto) {
        Result<PersonTaxNumber> taxNumber = PersonTaxNumber.create(studentDto.taxNumber);
        Result<PersonName> name = PersonName.create(studentDto.name);
        Result<EmailAddress> email = EmailAddress.create(studentDto.email);

        Result result = Result.combine(taxNumber, name, email);
        if(result.isFailure()){
            throw new EntityInvalidException(result.getError());
        }

        PersonDetail personDetail = new PersonDetail(taxNumber.value(), name.value(), email.value());
        Student student = new Student(personDetail);

        studentRepository.save(student);

        return student.getPersonDetail().getTaxNumber().getValue();
    }

    public void deleteOneByTaxNumber(String taxNumber){
        Optional<Student> studentOrNothing = studentRepository.findByPersonDetailTaxNumber(taxNumber);
        if(!studentOrNothing.isPresent())
            throw new EntityNotFoundException();

        studentRepository.delete(studentOrNothing.get());
    }

}
