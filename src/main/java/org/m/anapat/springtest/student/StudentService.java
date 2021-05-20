package org.m.anapat.springtest.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentsByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void editStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->  new IllegalStateException("student with id " + studentId + " does not exists"));

        if (name != null && name.length() > 0 &&
                !Objects.equals(student.getName(), name)
        ) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)
        ) {
            Optional<Student> studentOptional = studentRepository.findStudentsByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }


    }
}
