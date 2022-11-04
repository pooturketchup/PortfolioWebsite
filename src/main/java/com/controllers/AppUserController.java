package com.controllers;

import com.dto.AppUserDTO;
import com.models.AppUser;
import com.services.AppUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/users")
@AllArgsConstructor
@CrossOrigin
public class AppUserController {

    private final AppUserServiceImpl appUserService;

    @GetMapping
    public List<AppUserDTO> getAllUsers(){return appUserService.getAllUsers();}

    @GetMapping("/{id}")
    public AppUserDTO getUserById(@PathVariable Long id){return appUserService.getUserById(id);}

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUserDTO createNewUser(@RequestBody AppUser appUser){ return appUserService.createNewUser(appUser);}

    @PutMapping
    public AppUserDTO updateUser(@RequestBody AppUser appUser){return appUserService.updateUser(appUser);}

    @DeleteMapping("/{id}/delete")
    public void deleteUserById(@PathVariable Long id){ appUserService.deleteUserById(id);}
}
