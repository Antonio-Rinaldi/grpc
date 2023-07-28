package com.spring.grpc.grpcclient.configuration;

import com.spring.grpc.protobuf.api.UserApiGrpc;
import io.grpc.ManagedChannel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.inject.GrpcClientBean;
import net.devh.boot.grpc.client.inject.GrpcClientBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@GrpcClientBeans({
        @GrpcClientBean(clazz = UserApiGrpc.UserApiStub.class, beanName = "userApiStub", client = @GrpcClient("local")),
        @GrpcClientBean(clazz = UserApiGrpc.UserApiBlockingStub.class, beanName = "userApiBlockingStub", client = @GrpcClient("local")),
        @GrpcClientBean(clazz = UserApiGrpc.UserApiFutureStub.class, beanName = "userApiFutureStub", client = @GrpcClient("local"))
})
public class GrpcClientConfiguration {

// Se si vuole configurare il client manualmente.
//
//    private final String host;
//    private final Integer port;
//
//    public GrpcClientConfiguration(
//            @Value("${grpc.server.host}") String host,
//            @Value("${grpc.server.port}") Integer port) {\
//
//        this.host = host;
//        this.port = port;
//    }
//
//    @Bean
//    public ManagedChannel managedChannel() {
//        return ManagedChannelBuilder
//                .forAddress(host, port)
//                .usePlaintext()
//                .build();
//    }

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
