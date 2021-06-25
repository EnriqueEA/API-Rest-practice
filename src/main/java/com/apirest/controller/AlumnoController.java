package com.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.entity.Alumno;
import com.apirest.repository.AlumnoRepository;

@RestController
@RequestMapping("alumnos")
public class AlumnoController {
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@GetMapping
	public ResponseEntity<List<Alumno>> getAlumno() {
		List<Alumno> alumnos = alumnoRepository.findAll();
		return ResponseEntity.ok(alumnos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Alumno> getAlumno(@PathVariable("id") int id) {
		Optional<Alumno> alumno = alumnoRepository.findById(id);
		if(alumno.isPresent()) {
			return ResponseEntity.ok(alumno.get());
		} else {			
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST) //tambien @PostMapping
	public ResponseEntity<Alumno> insertAlumno(@RequestBody Alumno alumno) {
		Alumno newAlumno = alumnoRepository.save(alumno);
		return ResponseEntity.ok(newAlumno);
	}
	
	@DeleteMapping(value = "{idalumno}")
	public ResponseEntity<Alumno> deleteAlumno(@PathVariable("idalumno") int idalumno) {
		Optional<Alumno> alumno = alumnoRepository.findById(idalumno);
		if (alumno.isPresent()) {
			alumnoRepository.deleteById(alumno.get().getId());
			return ResponseEntity.ok(null);
		}else {
			return new ResponseEntity<Alumno>(alumno.get(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping
	public ResponseEntity<Alumno> updateAlumno(@RequestBody Alumno alumno) {
		Optional<Alumno> optionalAlumno = alumnoRepository.findById(alumno.getId());
		if(optionalAlumno.isPresent()) {
			Alumno updateAlumno = optionalAlumno.get();
			updateAlumno.setCorreo(alumno.getCorreo());
			updateAlumno.setDni(alumno.getDni());
			updateAlumno.setFechaNacimiento(alumno.getFechaNacimiento());
			updateAlumno.setNombre(alumno.getNombre());
			alumnoRepository.save(updateAlumno);
			return ResponseEntity.ok(updateAlumno);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	//private static final String template = "Hello, %s!";
	//private final AtomicInteger counter = new AtomicInteger();
	
	/*@RequestMapping("/hello")
	public Alumno greeting(@RequestParam(value="name", defaultValue="Word") String name) {
		return new Alumno(counter.incrementAndGet(), String.format(template, name));
	}*/
}
