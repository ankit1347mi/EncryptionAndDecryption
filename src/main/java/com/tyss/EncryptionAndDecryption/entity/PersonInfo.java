package com.tyss.EncryptionAndDecryption.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "person-info")
public class PersonInfo {
    @Id
    private String id;

    private String name;

    private String email;

    private String password;
}
