package com.nolanmortenson.portfolio.models;

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
    String username;
    String firstName;
    String lastName;
    String password;
    Timestamp lastLogin;
    @OneToMany
    List<AppUser> followers;

    @OneToMany
    List<AppUser> following;

    public AppUser(String email, String username, String firstName, String lastName,
                   String password, Timestamp timestamp, List<AppUser> followers, List<AppUser> following)
    {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.lastLogin = timestamp;
        this.followers = followers;
        this.following = following;
    }
}
