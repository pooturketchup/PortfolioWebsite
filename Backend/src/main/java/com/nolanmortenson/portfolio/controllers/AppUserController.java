package com.nolanmortenson.portfolio.controllers;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.exceptionHandlers.UserAlreadyExistsException;
import com.nolanmortenson.portfolio.exceptionHandlers.UserNotFoundException;
import com.nolanmortenson.portfolio.models.AppUser;
import com.nolanmortenson.portfolio.services.AppUserService;
import com.nolanmortenson.portfolio.services.AppUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
@CrossOrigin
public class AppUserController {

    private final AppUserService appUserService;

    private static final String USER_NOT_FOUND = "User not found";
    private static final String USER_ALREADY_IN_USE = "Email address or username already in use";

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
        return new ResponseEntity(USER_ALREADY_IN_USE, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity(USER_NOT_FOUND, HttpStatus.CONFLICT);
    }

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
