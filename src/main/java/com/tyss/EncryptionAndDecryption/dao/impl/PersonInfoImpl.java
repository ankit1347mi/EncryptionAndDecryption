package com.tyss.EncryptionAndDecryption.dao.impl;

import com.tyss.EncryptionAndDecryption.dao.PersonInfoDao;
import com.tyss.EncryptionAndDecryption.entity.PersonInfo;
import com.tyss.EncryptionAndDecryption.repo.PersonRepo;
import org.springframework.stereotype.Repository;

@Repository
public class PersonInfoImpl implements PersonInfoDao {

    private PersonRepo personRepo;

    public PersonInfoImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public PersonInfo savePerson(PersonInfo personInfo) {
        return personRepo.save(personInfo);
    }

    public PersonInfo findPersonById(String Id) {
        return personRepo.findById(Id).get();
    }

    @Override
    public PersonInfo findByEmailAndPassword(String email, String password) {
        return personRepo.findByEmailAndPassword(email, password);
    }

    @Override
    public PersonInfo findByEmail(String email) {
        return personRepo.findByEmail(email);
    }
}
