package com.udacity.jdnd.course3.critter.user;

import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerService customerService;

    private final EmployeeService employeeService;

    public UserController(CustomerService customerService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return CustomerDTO.createFromEntity(customerService.save(customerDTO.createEntity()));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        customerService.findAll().forEach(customer -> customerDTOs.add(CustomerDTO.createFromEntity(customer)));

        return customerDTOs;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return CustomerDTO.createFromEntity(customerService.findByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return EmployeeDTO.createFromEntity(employeeService.save(employeeDTO.createEntity()));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return EmployeeDTO.createFromEntity(employeeService.getById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        ArrayList<EmployeeDTO> employeeDTOs = new ArrayList<>();
        employeeService
                .findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate())
                .forEach(employee -> employeeDTOs.add(EmployeeDTO.createFromEntity(employee)));

        return employeeDTOs;
    }

}
