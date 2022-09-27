package com.greatlearning.student.service;

import java.util.List;
import com.greatlearning.student.entity.Student;

public interface StudentService
{
	public void save(Student student);
	public void deleteById(int id);
	public Student findById(int id);
	public List<Student> findAll();	
}
