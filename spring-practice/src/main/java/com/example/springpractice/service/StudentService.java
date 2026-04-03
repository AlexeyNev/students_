package com.example.springpractice.service;

import com.example.springpractice.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public StudentService() {
        students.add(new Student(1L, "Alex", 20));
        students.add(new Student(2L, "Alla", 19));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudentById(Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
    }

    public void deleteStudentById(Long id) {
        boolean removed = students.removeIf(student -> student
                .getId()
                .equals(id));

        if (!removed) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student not found"
            );
        }
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                // обновляем данные
                student.setName(updatedStudent.getName());
                student.setAge(updatedStudent.getAge());
                return student;
            }
        }

        throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                "Student not found"
        );
    }
}
