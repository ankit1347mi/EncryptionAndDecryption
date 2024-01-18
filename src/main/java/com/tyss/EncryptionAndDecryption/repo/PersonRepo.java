package com.tyss.EncryptionAndDecryption.repo;

import com.tyss.EncryptionAndDecryption.entity.PersonInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepo extends MongoRepository<PersonInfo, String> {

    PersonInfo findByEmailAndPassword(String email, String password);

    PersonInfo findByEmail(String email);
}
