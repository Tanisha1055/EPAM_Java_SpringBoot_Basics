package com.JavaSpringBootBasics.JavaSpringBootBasics.Service;

import com.JavaSpringBootBasics.JavaSpringBootBasics.DTO.UserDTO;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.User;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User convertToUser(UserDTO userDTO)
    {
        User user=new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
    public UserDTO convertToUserDTO(User user)
    {
        return new UserDTO(user.getId(),user.getName(), user.getEmail());
    }
    public UserDTO createUsers(UserDTO userDTO)
    {
        User user=convertToUser(userDTO);
        User savedUser=userRepository.save(user);
        return convertToUserDTO(savedUser);
    }
    public List<UserDTO> getAllUsers()
    {
        return userRepository.findAll()
                .stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }
    public Optional<UserDTO> getUserById(Long id)
    {
        return userRepository.findById(id)
                .map(this::convertToUserDTO);
        //this findById returns an Optional User , so the value can be present or absent.
        //so if the value is present then apply this function, otherwise don't apply.
    }
    public void deleteUserById(Long id)
    {
        userRepository.deleteById(id);

    }
    public UserDTO updateUserById(Long id,UserDTO userDTO)
    {
        User user=userRepository.findById(id).orElseThrow();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        User savedUser=userRepository.save(user);
        return convertToUserDTO(savedUser);
    }
}
