package com.apirest.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alumno")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idalumno")
	private int id;
	
	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name = "dni", nullable = false, length = 8)
	private String dni;

	@Column(nullable = false, length = 45)
	private String correo;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-DD")
	private Date fechaNacimiento;
}
