package com.tyss.EncryptionAndDecryption.service.impl;

import com.tyss.EncryptionAndDecryption.dao.PersonInfoDao;
import com.tyss.EncryptionAndDecryption.entity.PersonInfo;
import com.tyss.EncryptionAndDecryption.service.PersonService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@ConditionalOnProperty(prefix = "app", name = "config", havingValue = "cipher")
public class PersonServiceImplem implements PersonService {
    private final Cipher cipher;

    private final SecretKey secretKey;

    private PersonInfoDao personInfoDao;

    public PersonServiceImplem(Cipher cipher, SecretKey secretKey, PersonInfoDao personInfoDao) {
        this.cipher = cipher;
        this.secretKey = secretKey;
        this.personInfoDao = personInfoDao;
    }

    public String encrypt(String plaintext) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedText) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    @Override
    public ResponseEntity<?> savePerson(PersonInfo personInfo) {
        String password = personInfo.getPassword();
        try {
            String encrypt = encrypt(password);
            personInfo.setPassword(encrypt);
            personInfoDao.savePerson(personInfo);
            return new ResponseEntity<>(personInfo, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> findPersonById(String Id) throws Exception {
        PersonInfo personInfo = personInfoDao.findPersonById(Id);
        String decrypt = decrypt(personInfo.getPassword());
        personInfo.setPassword(decrypt);
        return new ResponseEntity<>(personInfo, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> findbyEmailAndPassword(String email, String password) throws Exception {
            PersonInfo personInfo = personInfoDao.findByEmail(email);
            String decrypt = decrypt(personInfo.getPassword());
            personInfo.setPassword(decrypt);
            return new ResponseEntity<>(personInfo, HttpStatus.CREATED);

    }
}
