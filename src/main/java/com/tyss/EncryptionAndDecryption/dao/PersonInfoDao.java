package com.tyss.EncryptionAndDecryption.dao;

import com.tyss.EncryptionAndDecryption.entity.PersonInfo;

public interface PersonInfoDao {

    PersonInfo savePerson(PersonInfo personInfo);

    PersonInfo findPersonById(String Id);

    PersonInfo findByEmailAndPassword(String email, String password);

    PersonInfo findByEmail(String email);
}
