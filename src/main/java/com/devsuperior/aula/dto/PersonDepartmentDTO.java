package com.devsuperior.aula.dto;

import com.devsuperior.aula.entities.Person;

public class PersonDepartmentDTO {

    private Long id;

    private String name;

    private Double salary;

    private DepartmentDTO departmentDTO;

    public PersonDepartmentDTO(){

    }

    public PersonDepartmentDTO(Long id, String name, Double salary, DepartmentDTO departmentDTO) { //construtor que recebe os atributos e copia para o dto
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.departmentDTO = departmentDTO;
    }

    public PersonDepartmentDTO(Person person) { //construtor que recebe a entidade e copia para o dto
        id = person.getId();
        name = person.getName();
        salary = person.getSalary();
        departmentDTO = new DepartmentDTO(person.getDepartment());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }
}
