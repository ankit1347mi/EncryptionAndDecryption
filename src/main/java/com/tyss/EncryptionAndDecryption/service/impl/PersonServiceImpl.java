package com.tyss.EncryptionAndDecryption.service.impl;

import com.tyss.EncryptionAndDecryption.dao.PersonInfoDao;
import com.tyss.EncryptionAndDecryption.entity.PersonInfo;
import com.tyss.EncryptionAndDecryption.service.PersonService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "app",name = "config",havingValue = "encoder")
public class PersonServiceImpl implements PersonService {

    private PersonInfoDao personInfoDao;

    private PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonInfoDao personInfoDao, PasswordEncoder passwordEncoder) {
        this.personInfoDao = personInfoDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> savePerson(PersonInfo personInfo) {
        String passord = personInfo.getPassword();
        String encode = passwordEncoder.encode(passord);
        personInfo.setPassword(encode);
        PersonInfo person = personInfoDao.savePerson(personInfo);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> findPersonById(String Id) {
        PersonInfo person = personInfoDao.findPersonById(Id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findbyEmailAndPassword(String email, String password) {
        PersonInfo personInfo = personInfoDao.findByEmail(email);
        boolean result = passwordEncoder.matches(password, personInfo.getPassword());
        if (result) {
            return new ResponseEntity<>(personInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
