package com.nolanmortenson.portfolio.services;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService{


    @Override
    public List<AppUserDTO> getAllUsers() {
        return null;
    }

    @Override
    public AppUserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public AppUserDTO createNewUser(AppUser appUser) {
        return null;
    }

    @Override
    public AppUserDTO updateUser(AppUser appUser) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
