package com.spring.grpc.grpcserver.controller;

import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import com.spring.grpc.protobuf.api.UserApiGrpc;
import com.spring.grpc.protobuf.model.User;
import com.spring.grpc.grpcserver.service.UserService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@GrpcService
@RequiredArgsConstructor
public class UserGrpcController extends UserApiGrpc.UserApiImplBase {

    private final UserService userService;

    public void getUser(StringValue request, StreamObserver<User> responseObserver) {
        try {
            User response = userService.getUser(request);
            responseObserver.onNext(response);

        } catch (Exception exception) {
            responseObserver.onError(exception);
        } finally {
            responseObserver.onCompleted();
        }
    }

    public void getUsers(Empty request, StreamObserver<User> responseObserver) {
        try {
            List<User> response = userService.getUsers();
            response.forEach(responseObserver::onNext);

        } catch (Exception exception) {
            responseObserver.onError(exception);
        } finally {
            responseObserver.onCompleted();
        }
    }

    public void saveUser(User request, StreamObserver<StringValue> responseObserver) {
        try {
            userService.saveUser(request);
            StringValue response = StringValue.newBuilder().setValue("User created!").build();
            responseObserver.onNext(response);

        } catch (Exception exception) {
            responseObserver.onError(exception);

        } finally {
            responseObserver.onCompleted();
        }
    }
}
