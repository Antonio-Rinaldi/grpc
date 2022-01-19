package com.spring.grpc.grpcserver.service;

import com.google.protobuf.StringValue;
import com.spring.grpc.protobuf.model.User;
import com.spring.grpc.grpcserver.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MongoService mongoService;


    public User getUser(StringValue username) {
        return this.entityToDto(
                mongoService.getUserOrElseThrow(username.getValue())
        );
    }

    public List<User> getUsers() {
        return mongoService.getAllUsers().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public void saveUser(User user) {
        mongoService.saveUser(
                this.dtoToEntity(user)
        );
    }

    private UserEntity dtoToEntity(User user) {
        return UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    private User entityToDto(UserEntity userEntity) {
        return User.newBuilder()
                .setUsername(userEntity.getUsername())
                .setPassword(userEntity.getPassword())
                .setEmail(userEntity.getEmail())
                .build();
    }
}
