package edu.miu.cs.cs425.studentmgmt.service;

import edu.miu.cs.cs425.studentmgmt.model.Student;
import edu.miu.cs.cs425.studentmgmt.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE / UPDATE
    @Transactional
    public Student saveStudent(Student student) {

        // ensure safe cascading for relationships
        if (student.getCourses() != null) {
            student.getCourses().forEach(course -> {
                course.getStudents().add(student);
            });
        }

        return studentRepository.save(student);
    }

    // READ ALL (uses EntityGraph → avoids N+1)
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // READ BY ID
    @Transactional(readOnly = true)
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // DELETE
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}