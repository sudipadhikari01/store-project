package com.sudip.store.electronicstore.services;

import com.sudip.store.electronicstore.dtos.PageableResponse;
import com.sudip.store.electronicstore.dtos.UserDto;

import java.util.List;

public interface UserService {
    //create
    UserDto createUser(UserDto user);

    //get all users
    PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

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
