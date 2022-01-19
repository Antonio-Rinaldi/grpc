package com.spring.grpc.grpcclient.service;

import com.google.common.collect.Lists;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import com.spring.grpc.protobuf.api.UserApiGrpc;
import com.spring.grpc.protobuf.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrpcClient {

    private final UserApiGrpc.UserApiBlockingStub userApiBlockingStub;

    public StringValue saveUser(User user) {
        return userApiBlockingStub.saveUser(user);
    }

    public User getUser(StringValue id) {
        return userApiBlockingStub.getUser(id);
    }

    public List<User> getUsers(Empty empty) {
        return Lists.newArrayList(userApiBlockingStub.getUsers(empty));
    }
}
