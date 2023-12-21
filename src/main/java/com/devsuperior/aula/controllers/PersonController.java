package com.devsuperior.aula.controllers;

import com.devsuperior.aula.dto.PersonDTO;
import com.devsuperior.aula.dto.PersonDepartmentDTO;
import com.devsuperior.aula.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDepartmentDTO> findById(@PathVariable Long id) { //@PathVariable id é o mesmo id da rota do @GetMapping {id}
        PersonDepartmentDTO dto = personService.findById(id);
        return ResponseEntity.ok(dto); //customizando o response
    }

    //@PostMapping  //comentado para não conflitar com o insert abaixo
    public ResponseEntity<PersonDepartmentDTO> insert(@RequestBody PersonDepartmentDTO dto) { //insert com o Department aninhado (DTO de Person contém um Department
        dto = personService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto); //passando o cod HTTP 201 de created
    }

    @PostMapping
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO dto) { //insert apenas com o id do departament, sem ter um Department no DTO
        dto = personService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto); //passando o cod HTTP 201 de created
    }



}
