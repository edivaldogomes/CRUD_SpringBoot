package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	public Optional<Student> getStudent(Long id) {
		return studentRepository.findById(id);
	}
	
	public void saveOrUpdate(Student student) {
		if (student.getEmail() == null || student.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be null or empty");
	    }
	    studentRepository.save(student);
	}
	
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}
	
}
