package com.tyss.EncryptionAndDecryption.controller;

import com.tyss.EncryptionAndDecryption.entity.PersonInfo;
import com.tyss.EncryptionAndDecryption.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<?> savePerson(@RequestBody PersonInfo personInfo) {
        return personService.savePerson(personInfo);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<?> findPersonById(@PathVariable String Id) throws Exception {
        return personService.findPersonById(Id);
    }

    @GetMapping("/login")
    public ResponseEntity<?> findPersonByEmailAndPassword(@RequestParam String email, @RequestParam String password) throws Exception {
        return personService.findbyEmailAndPassword(email, password);
    }
}
