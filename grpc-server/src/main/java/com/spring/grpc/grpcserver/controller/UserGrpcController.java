package com.spring.grpc.grpcserver.controller;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import com.spring.grpc.grpcserver.service.UserService;
import com.spring.grpc.protobuf.api.UserApiGrpc;
import com.spring.grpc.protobuf.model.User;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
@GrpcService
@RequiredArgsConstructor
public class UserGrpcController extends UserApiGrpc.UserApiImplBase {

    private final UserService userService;

    public void getUser(StringValue req, StreamObserver<User> responseObserver) {
        this.handleRequest(req, responseObserver, request ->
                Collections.singletonList(userService.getUser(request))
        );
    }

    public void getUsers(Empty req, StreamObserver<User> resObserver) {
        this.handleRequest(req, resObserver, request -> userService.getUsers());
    }

    public void saveUser(User req, StreamObserver<StringValue> resObserver) {
        this.handleRequest(req, resObserver, request -> {
            userService.saveUser(request);
            return Collections.singletonList(
                    StringValue.newBuilder().setValue("User created!").build()
            );
        });
    }


    public <REQ extends AbstractMessage, RES extends AbstractMessage> void handleRequest(
            REQ request,
            StreamObserver<RES> responseObserver,
            Function<REQ, List<RES>> requestHandler) {

        try {
            List<RES> response = requestHandler.apply(request);
            response.forEach(responseObserver::onNext);

        } catch (Exception exception) {
            responseObserver.onError(exception);
        } finally {
            responseObserver.onCompleted();
        }
    }
}
