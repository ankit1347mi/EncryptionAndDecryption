package com.tyss.EncryptionAndDecryption.service;

import com.tyss.EncryptionAndDecryption.entity.PersonInfo;
import org.springframework.http.ResponseEntity;

public interface PersonService {
    ResponseEntity<?> savePerson(PersonInfo personInfo);

    ResponseEntity<?> findPersonById(String Id) throws Exception;

    ResponseEntity<?> findbyEmailAndPassword(String email,String password) throws Exception;
}
