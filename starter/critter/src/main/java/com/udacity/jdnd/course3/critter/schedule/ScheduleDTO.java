package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public static ScheduleDTO createFromEntity(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, dto);

        dto.setEmployeeIds(new ArrayList<>());
        schedule.getEmployees().forEach(employee -> dto.addEmployeeId(employee.getId()));

        dto.setPetIds(new ArrayList<>());
        schedule.getPets().forEach(pet -> dto.addPetId(pet.getId()));

        return dto;
    }

    public Schedule createEntity() {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(this, schedule);
        // TODO: check if i need to get pets and employees and set them

        return schedule;
    }

    public static List<ScheduleDTO> convertEntityListToDTOs(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        schedules.forEach(schedule -> scheduleDTOs.add(createFromEntity(schedule)));

        return scheduleDTOs;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public void addEmployeeId(Long employeeId) { this.employeeIds.add(employeeId); }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public void addPetId(Long petId) {this.petIds.add(petId); }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
