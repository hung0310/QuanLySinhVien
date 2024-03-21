package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.StudentDto;
import com.example.demo.models.students;
import com.example.demo.services.StudentsRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentsController {
	@Autowired
	private StudentsRepository repo;
	
	@GetMapping({"", "/"})
	public String showStudents(Model model) {
		List<students> students = repo.findAll();
		model.addAttribute("students", students);
		return "students/index";
	}
	
	@GetMapping("/AddStudent")
	public String showCreatePage(Model model) {
		StudentDto studentDto = new StudentDto();
		model.addAttribute("studentDto", studentDto);
		return "students/AddStudent";
	}
	
	@PostMapping("/AddStudent")
	public String addStudent(
			@Valid @ModelAttribute StudentDto studentDto,
			BindingResult result) {
		if(result.hasErrors())
			return "students/AddStudent";
		
		
		students st = new students();
		st.setName(studentDto.getName());
		st.setAge(studentDto.getAge());
		
		repo.save(st);
		
		return "redirect:/students";
	}
	
	@GetMapping("/EditStudent")
	public String editStudent(
			Model model,
			@RequestParam int id) {
		
		try {
			students st = repo.findById(id).get();
			model.addAttribute("student", st);
			
			StudentDto studentDto = new StudentDto();
			studentDto.setName(st.getName());
			studentDto.setAge(st.getAge());
			
			model.addAttribute(studentDto);
		} catch(Exception ex) {
			return "redirect:/students";
		}
		
		return "students/EditStudent";
	}
	
	@PostMapping("/EditStudent")
	public String updateStudent(
			Model model,
			@RequestParam int id,
			@Valid @ModelAttribute StudentDto studentDto,
			BindingResult result) {
		
		try {
			students st = repo.findById(id).get();
			model.addAttribute("student", st);
			
			if(result.hasErrors()) 
				return "students/EditStudent";
			
			st.setName(studentDto.getName());
			st.setAge(studentDto.getAge());
			
			repo.save(st);
			
		} catch(Exception ex) {
			return "redirect:/students";
		}
		
		return "redirect:/students";
	}
	
	@GetMapping("/DeleteStudent") 
	public String deletStudent(@RequestParam int id) {
		
		try {
			students st = repo.findById(id).get();
			repo.delete(st);
		} catch(Exception ex) {}
		
		return "redirect:/students";
	}
}
