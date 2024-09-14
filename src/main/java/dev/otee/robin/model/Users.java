package dev.otee.robin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Table
public class Users {
    @Id
    int id;
    String username;
    OffsetDateTime createdAt;

    public Users(String username){
        this.username = username;
        this.createdAt = OffsetDateTime.now();
    }
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
