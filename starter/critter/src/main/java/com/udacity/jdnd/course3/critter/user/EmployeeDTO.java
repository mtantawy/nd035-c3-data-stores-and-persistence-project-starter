package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;

import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
public class EmployeeDTO {
    private long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;

    public static EmployeeDTO createFromEntity(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);

        return dto;
    }

    public Employee createEntity() {
        Employee employee = new Employee();
        BeanUtils.copyProperties(this, employee);

        return employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
