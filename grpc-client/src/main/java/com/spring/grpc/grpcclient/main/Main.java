package com.spring.grpc.grpcclient.main;

import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import com.spring.grpc.grpcclient.service.GrpcClient;
import com.spring.grpc.protobuf.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class Main {

    private final GrpcClient grpcClient;

    @Autowired
    public void main() {
        User user1 = User.newBuilder()
                .setUsername("User1")
                .setPassword("Password1")
                .setEmail("Email1")
                .build();

        User user2 = User.newBuilder()
                .setUsername("User2")
                .setPassword("Password2")
                .setEmail("Email2")
                .build();

        StringValue id1 = StringValue.newBuilder()
                .setValue(user1.getUsername())
                .build();

        StringValue id2 = StringValue.newBuilder()
                .setValue(user1.getUsername())
                .build();

        Empty empty = Empty.getDefaultInstance();

        grpcClient.saveUser(user1);
        grpcClient.saveUser(user2);
        User responseUser1 = grpcClient.getUser(id1);
        User responseUser2 = grpcClient.getUser(id2);
        List<User> responseUsers = grpcClient.getUsers(empty);

        log.info("User1:\n {}", responseUser1);
        log.info("User2:\n {}", responseUser2);
        log.info("Users:\n {}", responseUsers);
    }
}
