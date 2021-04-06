package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule save(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        employeeIds.forEach(employeeId -> schedule.addEmployee(employeeService.getById(employeeId)));
        petIds.forEach(petId -> schedule.addPet(petService.getById(petId)));

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    public List<Schedule> getScheduleForPet(Long petId) {
        return scheduleRepository.findAllByPetsId(petId);
    }

    public List<Schedule> getScheduleForPetOwner(Long petOwnerId) {
        return scheduleRepository.findAllByPetsOwnerId(petOwnerId);
    }
}
