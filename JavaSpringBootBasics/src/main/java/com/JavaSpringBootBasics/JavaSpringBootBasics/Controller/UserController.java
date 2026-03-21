package com.JavaSpringBootBasics.JavaSpringBootBasics.Controller;

import com.JavaSpringBootBasics.JavaSpringBootBasics.DTO.UserDTO;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public UserDTO createUsers(@RequestBody UserDTO userDTO)
    {
        return userService.createUsers(userDTO);
    }
    @GetMapping
    public List<UserDTO> getAllUsers()
    {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public Optional<UserDTO> getUsersById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }
    @PutMapping("/{id}")
    public UserDTO updateUserById(@PathVariable Long id, UserDTO userDTO)
    {
        return userService.updateUserById(id,userDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id)
    {
         userService.deleteUserById(id);
    }
}
