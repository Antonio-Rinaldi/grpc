package com.spring.grpc.grpcclient.configuration;

import com.spring.grpc.protobuf.api.UserApiGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfiguration {

    private final String host;
    private final Integer port;

    public GrpcClientConfiguration(
            @Value("${grpc.server.host}") String host,
            @Value("${grpc.server.port}") Integer port) {

        this.host = host;
        this.port = port;
    }

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }

    @Bean
    public UserApiGrpc.UserApiStub userApiStub(ManagedChannel managedChannel) {
        return UserApiGrpc.newStub(managedChannel);
    }

    @Bean
    public UserApiGrpc.UserApiBlockingStub userApiBlockingStub(ManagedChannel managedChannel) {
        return UserApiGrpc.newBlockingStub(managedChannel);
    }

    @Bean
    public UserApiGrpc.UserApiFutureStub userApiFutureStub(ManagedChannel managedChannel) {
        return UserApiGrpc.newFutureStub(managedChannel);
    }
}
