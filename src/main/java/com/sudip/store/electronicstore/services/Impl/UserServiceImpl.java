package com.sudip.store.electronicstore.services.Impl;

import com.sudip.store.electronicstore.dtos.UserDto;
import com.sudip.store.electronicstore.entity.User;
import com.sudip.store.electronicstore.exception.ResourceNotFoundException;
import com.sudip.store.electronicstore.repo.UserRepo;
import com.sudip.store.electronicstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        String userId = UUID.randomUUID().toString();
        User user = mapper.map(userDto, User.class);
        user.setUserId(userId);
        User savedUser = userRepo.save(user);

        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> list = users.stream().map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with Id:" + userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setAbout(userDto.getAbout());
        user.setImageName(userDto.getImageName());
        user.setPassword(userDto.getPassword());
        User savedUser = userRepo.save(user);

        return mapper.map(savedUser, UserDto.class);

    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with Id:" + userId));
        userRepo.delete(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with Id:" + userId));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findUserByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User not found with provided email: " + email)
        );
        return mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getByUserName(String name) {
        List<User> users = userRepo.findUserByNameContaining(name);
        List<UserDto> collect = users.stream().map(user -> mapper
                .map(user, UserDto.class)).collect(Collectors.toList());
        return collect;
    }
}
