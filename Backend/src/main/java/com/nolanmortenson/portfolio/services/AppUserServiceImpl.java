package com.nolanmortenson.portfolio.services;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.exceptionHandlers.UserAlreadyExistsException;
import com.nolanmortenson.portfolio.exceptionHandlers.UserNotFoundException;
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

    private final AppUserRepo appUserRepo;
    private final MapStructMapper mapper;
    @Override
    public List<AppUserDTO> getAllUsers() {
        return mapper.appUserListToDtoList(appUserRepo.findAll());
    }

    @Override
    public AppUserDTO getUserById(Long id) {
        if(!appUserRepo.existsById(id)){
            throw new UserNotFoundException();
        }

        return mapper.appUserToDto(appUserRepo.findById(id).orElse(null));
    }

    @Override
    public AppUserDTO createNewUser(AppUser appUser) throws UserAlreadyExistsException{
        if(appUserRepo.existsByUsernameOrEmail(appUser.getUsername(),appUser.getEmail())){
            throw new UserAlreadyExistsException();
        }

        return mapper.appUserToDto(appUserRepo.save(appUser));
    }

    @Override
    public AppUserDTO updateUser(AppUser appUserUpdate) throws UserNotFoundException {
       if(!appUserRepo.existsById(appUserUpdate.getId()) || appUserUpdate == null)
       {
           throw new UserNotFoundException();
       }

       AppUser appUser = appUserRepo.findById(appUserUpdate.getId()).orElse(null);

       appUser.setEmail(appUserUpdate.getEmail());
       appUser.setUsername(appUserUpdate.getUsername());
       appUser.setFirstName(appUserUpdate.getFirstName());
       appUser.setLastName(appUserUpdate.getLastName());
       appUser.setLastLogin(appUserUpdate.getLastLogin());
       appUser.setFollowing(appUserUpdate.getFollowing());
       appUser.setFollowers(appUserUpdate.getFollowers());
       appUser.setPassword(appUserUpdate.getPassword());

       return mapper.appUserToDto(appUserRepo.save(appUser));
    }

    @Override
    public void deleteUserById(Long id) throws UserNotFoundException {
        if(!appUserRepo.existsById(id)){
            throw new UserNotFoundException();
        }

        appUserRepo.deleteById(id);
    }
}
