package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EntityManager entityManager;

    public Pet save(Pet pet, Long ownerId) {
        Customer owner = customerService.getById(ownerId);
        pet.setOwner(owner);
        pet = petRepository.save(pet);

        owner.addPet(pet);

        return pet;
    }

    public Pet getById(Long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public List<Pet> findAllByOwner(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }
}
