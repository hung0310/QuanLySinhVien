package com.example.demo.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.students;

public interface StudentsRepository  extends JpaRepository<students, Integer>{

}
