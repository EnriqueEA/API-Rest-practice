package com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{

}
