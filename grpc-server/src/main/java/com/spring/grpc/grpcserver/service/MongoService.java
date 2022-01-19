package com.spring.grpc.grpcserver.service;

import com.spring.grpc.grpcserver.model.UserEntity;
import com.spring.grpc.grpcserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MongoService {

    private final UserRepository userRepository;


    public Optional<UserEntity> getUser(String id) {
        return userRepository.findById(id);
    }

    public UserEntity getUserOrElseThrow(String id) {
        return this.getUser(id).orElseThrow();
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
