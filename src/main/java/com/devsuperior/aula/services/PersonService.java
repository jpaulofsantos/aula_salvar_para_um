package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.PersonDepartmentDTO;
import com.devsuperior.aula.entities.Department;
import com.devsuperior.aula.entities.Person;
import com.devsuperior.aula.repositories.DepartmentRepository;
import com.devsuperior.aula.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public PersonDepartmentDTO findById(Long id) {
        Person result = personRepository.findById(id).orElseThrow(); //busca no banco de dados o objeto passando o ID e lança exceção se necessário
        return new PersonDepartmentDTO(result); //cria um novo productDTO passando o product recuperado do banco
    }

    @Transactional
    public PersonDepartmentDTO insert(PersonDepartmentDTO dto){
        Person person = new Person();
        person.setName(dto.getName()); //copiando os dados do dto para a entidade person
        person.setSalary(dto.getSalary());

        Department department = departmentRepository.getReferenceById(dto.getDepartmentDTO().getId());

        //Department department = new Department();
        //department.setId(dto.getDepartmentDTO().getId());

        person.setDepartment(department);

        person = personRepository.save(person);
        return new PersonDepartmentDTO(person);

    }
}
