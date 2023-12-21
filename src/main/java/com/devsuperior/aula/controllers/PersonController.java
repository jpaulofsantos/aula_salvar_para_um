package com.devsuperior.aula.controllers;

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

    @PostMapping
    public ResponseEntity<PersonDepartmentDTO> insert(@RequestBody PersonDepartmentDTO dto) { //o corpo da requisição que chega, entra nesse parametro e instancia um novo dto. @Valid verifica
        dto = personService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto); //passando o cod HTTP 201 de created
    }



}
