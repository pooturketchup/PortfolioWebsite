package com.nolanmortenson.portfolio.services;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.models.AppUser;

import java.util.List;

public interface AppUserService {

    public List<AppUserDTO> getAllUsers();
    public AppUserDTO getUserById(Long id);
    public AppUserDTO createNewUser(AppUser appUser);
    public AppUserDTO updateUser(AppUser appUser);
    public void deleteUserById(Long id);
}
