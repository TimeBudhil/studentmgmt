package edu.miu.cs.cs425.studentmgmt;

import edu.miu.cs.cs425.studentmgmt.model.*;
import edu.miu.cs.cs425.studentmgmt.service.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MyStudentMgmtApp implements CommandLineRunner {

    private final StudentService studentService;
    private final TranscriptService transcriptService;
    private final ClassroomService classroomService;
    private final CourseService courseService;

    public MyStudentMgmtApp(StudentService studentService,
                            TranscriptService transcriptService,
                            ClassroomService classroomService,
                            CourseService courseService) {
        this.studentService = studentService;
        this.transcriptService = transcriptService;
        this.classroomService = classroomService;
        this.courseService = courseService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyStudentMgmtApp.class, args);
    }

    @Override
    public void run(String... args) {

        // =========================
        // 1. TRANSCRIPT
        // =========================
        Transcript transcript = new Transcript(
                1L,
                "BS Computer Science"
        );
        transcriptService.saveTranscript(transcript);

        // =========================
        // 2. CLASSROOM
        // =========================
        Classroom classroom = new Classroom(
                1L,
                "McLaughlin building",
                "M105"
        );
        classroomService.saveClassroom(classroom);

        // =========================
        // 3. COURSES
        // =========================
        Course course1 = new Course(
                1L,
                "CS401",
                "Modern Programming Practices"
        );

        Course course2 = new Course(
                2L,
                "CS425",
                "Software Engineering"
        );

        courseService.saveCourse(course1);
        courseService.saveCourse(course2);

        // =========================
        // 4. STUDENT
        // =========================
        Student student = new Student(
                1L,
                "000-61-0001",
                "Anna",
                "Lynn",
                "Smith",
                3.45,
                LocalDate.of(2019, 5, 24),
                "Yes"
        );

        // set relationships
        student.setTranscript(transcript);
        student.setClassroom(classroom);

        student.getCourses().add(course1);
        student.getCourses().add(course2);

        // IMPORTANT:
        // Student is owning side of ALL relationships → save only student
        studentService.saveStudent(student);

        System.out.println("=== DATA SAVED SUCCESSFULLY ===");
    }
}