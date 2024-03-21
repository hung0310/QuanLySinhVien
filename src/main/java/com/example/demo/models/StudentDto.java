package com.example.demo.models;

import jakarta.validation.constraints.NotEmpty;

public class StudentDto {
	@NotEmpty(message = "Không được bỏ trống")
	private String name;
	
	@NotEmpty(message = "Không được bỏ trống")
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
