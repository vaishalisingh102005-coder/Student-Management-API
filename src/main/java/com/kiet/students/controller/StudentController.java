
package com.kiet.students.controller;

import com.kiet.students.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(101, "Rahul Sharma", "rahul@kiet.edu",
                            "CSE(AI & ML)", 2, 8.65),
                    new Student(102, "Priya Singh", "priya@kiet.edu",
                            "CSE", 2, 9.20),
                    new Student(103, "Aman Verma", "aman@kiet.edu",
                            "IT", 3, 8.10),
                    new Student(104, "Neha Gupta", "neha@kiet.edu",
                            "CSE", 2, 9.10),
                    new Student(105, "Rohit Kumar", "rohit@kiet.edu",
                            "ECE", 1, 7.80)
            )
    );

    // 1. GET: Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    // 2. GET: Get student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentById(
            @PathVariable int studentId) {

        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                return ResponseEntity.ok(s);
            }
        }

        return ResponseEntity.status(404)
                .body(Map.of("message", "Student not found"));
    }

    // 3. POST: Add a new student
    @PostMapping
    public ResponseEntity<?> addStudent(
            @RequestBody Student newStudent) {

        for (Student s : students) {
            if (s.getStudentId() == newStudent.getStudentId()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message",
                                "Student ID already exists"));
            }
        }

        students.add(newStudent);

        return ResponseEntity.status(201).body(newStudent);
    }

    // 4. PUT: Update an existing student
    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int studentId,
            @RequestBody Student updatedStudent) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getStudentId() == studentId) {

                updatedStudent.setStudentId(studentId);
                students.set(i, updatedStudent);

                return ResponseEntity.ok(updatedStudent);
            }
        }

        return ResponseEntity.status(404)
                .body(Map.of("message", "Student not found"));
    }

    // 5. DELETE: Delete a student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Map<String, String>> deleteStudent(
            @PathVariable int studentId) {

        boolean removed = students.removeIf(
                s -> s.getStudentId() == studentId);

        if (removed) {
            return ResponseEntity.ok(
                    Map.of("message", "Student deleted successfully"));
        }

        return ResponseEntity.status(404).body(
                Map.of("message", "Student not found"));
    }

    // 6. GET: Search by branch and year
    @GetMapping("/search")
    public List<Student> searchStudents(
            @RequestParam String branch,
            @RequestParam int year) {

        List<Student> result = new ArrayList<>();

        for (Student s : students) {
            if (s.getBranch().toLowerCase()
                    .contains(branch.toLowerCase())
                    && s.getYear() == year) {

                result.add(s);
            }
        }

        return result;
    }

    // 7. GET: Find students with minimum CGPA
    @GetMapping("/topper")
    public List<Student> getToppers(
            @RequestParam double cgpa) {

        List<Student> result = new ArrayList<>();

        for (Student s : students) {
            if (s.getCgpa() >= cgpa) {
                result.add(s);
            }
        }

        return result;
    }
}
