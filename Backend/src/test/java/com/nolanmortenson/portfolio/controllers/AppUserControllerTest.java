package com.nolanmortenson.portfolio.controllers;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.models.AppUser;
import com.nolanmortenson.portfolio.services.AppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AppUserControllerTest {

    @InjectMocks
    AppUserController appUserController;

    @Mock
    AppUserServiceImpl appUserService;

    AppUser appUser;

    AppUserDTO appUserDTO;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        appUser = new AppUser();
        appUser.setId(1L);

        appUserDTO = new AppUserDTO();
        appUserDTO.setId(1L);

        mockMvc = MockMvcBuilders.standaloneSetup(appUserController).build();
    }

    @Test
    void getAllUsers_StatusOK() throws Exception{
        //given
        List<AppUserDTO> users = new ArrayList<>();

        //when
        when(appUserService.getAllUsers()).thenReturn(users);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById_StatusOk() throws Exception {
        //when
        when(appUserService.getUserById(anyLong())).thenReturn(appUserDTO);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void createNewUser_StatusOk() throws Exception{
        //when
        when(appUserService.createNewUser(appUser)).thenReturn(appUserDTO);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"first_Name\":0}")
        ).andExpect(status().isCreated());
    }

    @Test
    void updateUser_StatusOk() throws Exception  {
        //when
        when(appUserService.updateUser(appUser)).thenReturn(appUserDTO);

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1}")
        ).andExpect(status().isOk());
    }

    @Test
    void deleteUserById() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());
    }
}