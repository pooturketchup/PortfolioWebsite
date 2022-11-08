package com.nolanmortenson.portfolio.services;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.mappers.MapStructMapper;
import com.nolanmortenson.portfolio.models.AppUser;
import com.nolanmortenson.portfolio.repositories.AppUserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService{

    AppUserRepo appUserRepo;
    MapStructMapper mapper;
    @Override
    public List<AppUserDTO> getAllUsers() {
        return mapper.appUserListToDtoList(appUserRepo.findAll());
    }

    @Override
    public AppUserDTO getUserById(Long id) {
        return mapper.appUserToDto(appUserRepo.findById(id).orElse(null));
    }

    @Override
    public AppUserDTO createNewUser(AppUser appUser) {
        if(appUserRepo.existsById(appUser.getId())){
            //throw error
        }

        AppUser convert = appUserRepo.save(appUser);

        return mapper.appUserToDto(convert);
    }

    @Override
    public AppUserDTO updateUser(AppUser appUserUpdate) {
       if(!appUserRepo.existsById(appUserUpdate.getId()))
       {
           //throw error
       }

       AppUser appUser = appUserRepo.findById(appUserUpdate.getId()).orElse(null);

       appUser.setEmail(appUserUpdate.getEmail());
       appUser.setFirstName(appUserUpdate.getFirstName());
       appUser.setLastName(appUserUpdate.getLastName());
       appUser.setLastLogin(appUserUpdate.getLastLogin());
       appUser.setFollowing(appUserUpdate.getFollowing());
       appUser.setFollowers(appUserUpdate.getFollowers());
       appUser.setPassword(appUserUpdate.getPassword());

       return mapper.appUserToDto(appUser);
    }

    @Override
    public void deleteUserById(Long id) {
        if(!appUserRepo.existsById(id)){
            //throw error
        }

        appUserRepo.deleteById(id);
    }
}
