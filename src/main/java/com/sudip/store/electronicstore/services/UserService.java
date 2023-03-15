package com.sudip.store.electronicstore.services;

import com.sudip.store.electronicstore.dtos.UserDto;

import java.util.List;

public interface UserService {
    //create
    UserDto createUser(UserDto user);

    //get all users
    List<UserDto> getAllUsers();

    //update
    UserDto updateUser(UserDto userDto, String userId);

    //delete
    void deleteUser(String userId);

    //    get single user by id
    UserDto getUserById(String id);

    //    get user by email
    UserDto getUserByEmail(String email);

    //    get user by name
    List<UserDto> getByUserName(String name);


}
