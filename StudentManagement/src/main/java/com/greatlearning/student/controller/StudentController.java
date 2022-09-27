package com.greatlearning.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.student.entity.Student;
import com.greatlearning.student.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentController 
{
	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudents(Model model)
	{
		List<Student> students = studentService.findAll();

		model.addAttribute("Students", students);

		return "list-students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) 
	{
		Student student = new Student();

		model.addAttribute("Student", student);

		return "add-student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int id, Model model)
	{
		Student student = studentService.findById(id);

		model.addAttribute("Student", student);

		return "add-student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, 
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("department") String department,
			@RequestParam("country") String country)
	{
		System.out.println(id);
		Student student;

		if (id != 0) 
		{
			student = studentService.findById(id);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setDepartment(department);
			student.setCountry(country);
		}
		else
		{
			student = new Student(firstName, lastName, department, country);
		}

		// save the Student
		studentService.save(student);

		// use a redirect to prevent duplicate submissions
		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id) {

		// delete the Book
		studentService.deleteById(id);

		// redirect to /Student/list
		return "redirect:/student/list";

	}


}