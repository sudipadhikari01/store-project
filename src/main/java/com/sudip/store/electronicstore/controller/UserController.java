package com.sudip.store.electronicstore.controller;

import com.sudip.store.electronicstore.dtos.UserDto;
import com.sudip.store.electronicstore.payload.ResponseMessage;
import com.sudip.store.electronicstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> collect = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));

            return new ResponseEntity(collect, HttpStatus.BAD_REQUEST);
        }
        UserDto userDto1 = userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    // read
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUser() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String userId,
                                              @Valid @RequestBody UserDto userDto) {
        UserDto userDto1 = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);

    }

    //    delete single user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        ResponseMessage responseMessage = ResponseMessage.builder()
                .message("User Deleted successfully!!")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    //    get single user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findSingleUser(@PathVariable String id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //    get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable String email) {
        UserDto userDto = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //search user
    @GetMapping("/search/{id}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable("id") String name) {
        List<UserDto> byUserName = userService.getByUserName(name);
        return new ResponseEntity<>(byUserName, HttpStatus.OK);
    }

}
