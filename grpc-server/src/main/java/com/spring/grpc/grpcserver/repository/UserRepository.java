package com.spring.grpc.grpcserver.repository;

import com.spring.grpc.grpcserver.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
