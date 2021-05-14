package com.theguitarstore.gs_client.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Julian
 */
@Document(collection="Client")
public class Client {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    
    @Id
    private long id;
    
    @Size(max = 100)
    @Indexed(unique = true)
    @NotBlank
    private String name;
    
    private String phoneNumber;
    
    @Indexed(unique = true)
    @Size(max = 100)
    @NotBlank
    private String email;

    public Client() {
    }

    public Client(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + '}';
    }
    
    
    
}
