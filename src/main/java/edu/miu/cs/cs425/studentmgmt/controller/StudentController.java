package edu.miu.cs.cs425.studentmgmt.controller;

import edu.miu.cs.cs425.studentmgmt.model.Student;
import edu.miu.cs.cs425.studentmgmt.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eregistrar/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // LIST STUDENTS
    @GetMapping("/list")
    public ResponseEntity<List<Student>> listStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // REGISTER NEW STUDENT
    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    // READ STUDENT BY ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE STUDENT BY ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student student) {
        return studentService.getStudentById(id)
                .map(existingStudent -> {
                    existingStudent.setStudentNumber(student.getStudentNumber());
                    existingStudent.setFirstName(student.getFirstName());
                    existingStudent.setMiddleName(student.getMiddleName());
                    existingStudent.setLastName(student.getLastName());
                    existingStudent.setCgpa(student.getCgpa());
                    existingStudent.setEnrollmentDate(student.getEnrollmentDate());
                    existingStudent.setIsInternational(student.getIsInternational());
                    existingStudent.setClassroom(student.getClassroom());
                    existingStudent.setTranscript(student.getTranscript());
                    existingStudent.setCourses(student.getCourses());
                    return ResponseEntity.ok(studentService.saveStudent(existingStudent));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE STUDENT BY ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}