package com.nolanmortenson.portfolio.services;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.exceptionHandlers.UserAlreadyExistsException;
import com.nolanmortenson.portfolio.exceptionHandlers.UserNotFoundException;
import com.nolanmortenson.portfolio.models.AppUser;

import java.util.List;

public interface AppUserService {

    public List<AppUserDTO> getAllUsers();
    public AppUserDTO getUserById(Long id) throws UserNotFoundException;
    public AppUserDTO createNewUser(AppUser appUser) throws UserAlreadyExistsException;
    public AppUserDTO updateUser(AppUser appUser) throws UserNotFoundException;
    public void deleteUserById(Long id) throws UserNotFoundException;
}
