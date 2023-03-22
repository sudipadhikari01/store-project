package com.sudip.store.electronicstore.controller;

import com.sudip.store.electronicstore.dtos.PageableResponse;
import com.sudip.store.electronicstore.dtos.UserDto;
import com.sudip.store.electronicstore.payload.ImageResponse;
import com.sudip.store.electronicstore.payload.ResponseMessage;
import com.sudip.store.electronicstore.services.FileService;
import com.sudip.store.electronicstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imagePath;

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

    // find all users
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> findAllUser(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                                 @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
                                                                 @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
                                                                 @RequestParam(value = "sortDir", defaultValue = "Asc", required = false) String sortDir) {
        PageableResponse<UserDto> allUsers = userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);
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

    //search  user by name
    @GetMapping("/search/{name}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable("name") String name) {
        List<UserDto> byUserName = userService.getByUserName(name);
        return new ResponseEntity<>(byUserName, HttpStatus.OK);
    }

    //    upload image
    @PostMapping("/upload/{userId}")
    public ResponseEntity<ImageResponse> uploadImage(@PathVariable String userId, @RequestParam MultipartFile userImage) {
        String fullPath = fileService.uploadFile(userImage, imagePath);
        UserDto userById = userService.getUserById(userId);
        userById.setImageName(fullPath);
        UserDto userDto = userService.updateUser(userById, userId);

        ImageResponse imageResponse = ImageResponse.builder().imageName(fullPath).message("Image uploaded successfully")
                .httpStatus(HttpStatus.CREATED).success(true).build();

        return new ResponseEntity(imageResponse, HttpStatus.CREATED);

    }

}
