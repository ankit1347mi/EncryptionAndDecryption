package com.tyss.EncryptionAndDecryption.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


@Configuration
public class CipherConfig {

    @Bean
    public Cipher cipher() throws Exception {
        return Cipher.getInstance("AES");
    }

    @Bean
    public SecretKey secretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

}
