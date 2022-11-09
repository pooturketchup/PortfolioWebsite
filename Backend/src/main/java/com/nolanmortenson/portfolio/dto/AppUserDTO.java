package com.nolanmortenson.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    Long id;
    String email;
    String username;
    String firstName;
    String lastName;
    Timestamp lastLogin;
    List<AppUserDTO> followers;
    List<AppUserDTO> following;
}
