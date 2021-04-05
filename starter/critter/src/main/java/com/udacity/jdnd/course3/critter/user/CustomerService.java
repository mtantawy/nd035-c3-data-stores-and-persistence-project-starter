package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetService petService;

    @Autowired
    private EntityManager entityManager;

    public Customer getById(Long customerId) {
        return customerRepository.getOne(customerId);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() { return customerRepository.findAll(); }

    public Customer findByPetId(Long petId) {
        return petService.getById(petId).getOwner();
    }
}
