package org.heyjiobum.fintrackbackend.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private String username;
    private String password;
    private String roles = Role.User.getName();

    public MyUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
