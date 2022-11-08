package com.nolanmortenson.portfolio.controllers;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.models.AppUser;
import com.nolanmortenson.portfolio.services.AppUserService;
import com.nolanmortenson.portfolio.services.AppUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
@CrossOrigin
public class AppUserController {

    private final AppUserService appUserService;

//    public AppUserController(AppUserService appUserService_)
//    {
//        appUserService = appUserService_;
//    }

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
