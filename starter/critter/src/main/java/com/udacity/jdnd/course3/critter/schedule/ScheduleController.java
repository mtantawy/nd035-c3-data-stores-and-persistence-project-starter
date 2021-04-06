package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.udacity.jdnd.course3.critter.schedule.ScheduleDTO.convertEntityListToDTOs;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return ScheduleDTO.createFromEntity(
                scheduleService.save(scheduleDTO.createEntity(), scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds()));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return convertEntityListToDTOs(scheduleService.findAll());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return convertEntityListToDTOs(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return convertEntityListToDTOs(scheduleService.getScheduleForEmployee(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return convertEntityListToDTOs(scheduleService.getScheduleForPetOwner(customerId));
    }
}
