package com.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String email;
    String firstName;
    String lastName;
    String password;
    Timestamp lastLogin;
    @OneToMany
    List<AppUser> followers;

    @OneToMany
    List<AppUser> following;
}
