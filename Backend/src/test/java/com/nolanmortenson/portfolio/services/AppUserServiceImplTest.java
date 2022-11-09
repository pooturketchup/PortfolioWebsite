package com.nolanmortenson.portfolio.services;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.exceptionHandlers.UserAlreadyExistsException;
import com.nolanmortenson.portfolio.exceptionHandlers.UserNotFoundException;
import com.nolanmortenson.portfolio.mappers.MapStructMapper;
import com.nolanmortenson.portfolio.mappers.MapStructMapperImpl;
import com.nolanmortenson.portfolio.models.AppUser;
import com.nolanmortenson.portfolio.repositories.AppUserRepo;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AppUserServiceImplTest {
    private final MapStructMapper mapper = new MapStructMapperImpl();

    @InjectMocks
    AppUserServiceImpl appUserService;

    @Mock
    AppUserRepo appUserRepo;

    AppUser appUser;

    AppUserDTO appUserDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        appUserService = new AppUserServiceImpl(appUserRepo,mapper);

        appUser = new AppUser(1L, "bruh@gmail.com","DrRooster45","Kevin","Mills","password",null,null,null);
        appUserDTO = new AppUserDTO(appUser.getId(),appUser.getEmail(), appUser.getUsername(), appUser.getFirstName(), appUser.getLastName(),appUser.getLastLogin(), null, null);
    }


    @Test
    void getUserById() {
        //when
        when(appUserRepo.existsById(anyLong())).thenReturn(true);
        when(appUserRepo.findById(anyLong())).thenReturn(Optional.of(appUser));

        //then
        assertEquals(appUserDTO, appUserService.getUserById(appUser.getId()));
    }

    @Test
    void getUserByIdFailure(){
        //when
        when(appUserRepo.existsById(anyLong())).thenReturn(false);

        //then
        Assertions.assertThrows(UserNotFoundException.class, () -> appUserService.getUserById(1L));
    }

    @Test
    void createNewUser() {
        //when
        when(appUserRepo.existsByUsernameOrEmail(anyString(), anyString())).thenReturn(false);
        when(appUserRepo.save(any())).thenReturn(appUser);
        //then
        Assertions.assertEquals(appUserDTO, appUserService.createNewUser(appUser));
        verify(appUserRepo, times(1)).save(appUser);
    }

    @Test
    void createNewUserFailure(){
        //when
        when(appUserRepo.existsByUsernameOrEmail(anyString(),anyString())).thenReturn(true);

        //then
        Assertions.assertThrows(UserAlreadyExistsException.class, ()->appUserService.createNewUser(appUser));
    }

    @Test
    void updateUser() {
        //given
        AppUser appUserBeforeUpdate = new AppUser();
        appUserBeforeUpdate.setId(appUser.getId());
        AppUser appUserAfterUpdate = new AppUser(appUser.getId(),"JohnDoe@gmail.com", "JBuck37",
                "John", "Doe","password",null,null,null);
        //when
        when(appUserRepo.existsById(anyLong())).thenReturn(true);
        when(appUserRepo.findById(anyLong())).thenReturn(Optional.of(appUserBeforeUpdate));
        when(appUserRepo.save(any())).thenReturn(appUserAfterUpdate);
        appUserService.updateUser(appUserAfterUpdate);

        //then
        assertEquals(appUserAfterUpdate, appUserBeforeUpdate);
        verify(appUserRepo, times(1)).save(appUserAfterUpdate);
    }

    @Test
    void updateUserFailure() {
        //given
        AppUser appUserBeforeUpdate = new AppUser();
        appUserBeforeUpdate.setId(appUser.getId());
        AppUser appUserAfterUpdate = new AppUser(appUser.getId(),"JohnDoe@gmail.com", "JBuck37",
                "John", "Doe","password",null,null,null);
        //when
        when(appUserRepo.existsById(anyLong())).thenReturn(false);;

        //then
       assertThrows(UserNotFoundException.class, ()->appUserService.updateUser(appUserAfterUpdate));
    }

    @Test
    void deleteUserById() {
        //when
        when(appUserRepo.existsById(anyLong())).thenReturn(true);
        appUserService.deleteUserById(appUser.getId());
        //then
        verify(appUserRepo, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteUserByIdFailure() {
        //when
        when(appUserRepo.existsById(anyLong())).thenReturn(false);
        //then
        Assertions.assertThrows(UserNotFoundException.class, ()->appUserService.deleteUserById(appUser.getId()));
    }
}