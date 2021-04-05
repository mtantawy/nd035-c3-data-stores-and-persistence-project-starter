package com.udacity.jdnd.course3.critter.pet;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return PetDTO.createFromEntity(petService.save(petDTO.createEntity(), petDTO.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return PetDTO.createFromEntity(petService.getById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        ArrayList<PetDTO> petDTOs = new ArrayList<>();
        petService.findAll().forEach(pet -> petDTOs.add(PetDTO.createFromEntity(pet)));

        return petDTOs;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        ArrayList<PetDTO> petDTOs = new ArrayList<>();
        petService.findAllByOwner(ownerId).forEach(pet -> petDTOs.add(PetDTO.createFromEntity(pet)));

        return petDTOs;
    }
}
