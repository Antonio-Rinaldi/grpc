package com.spring.grpc.grpcserver.configuration;

import com.spring.grpc.grpcserver.controller.UserGrpcController;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class GrpcServerConfiguration {

    private final Integer grpcServerPort;
    private final UserGrpcController userGrpcController;

    public GrpcServerConfiguration(
            UserGrpcController userGrpcController,
            @Value("${grpc.server.port}") Integer grpcServerPort) {

        this.grpcServerPort = grpcServerPort;
        this.userGrpcController = userGrpcController;
    }

    @Bean
    Boolean initServer(Server server) {
        try {
            log.info("Starting grpc server...");
            server.start();

            log.info("Server listening at port {}...", grpcServerPort);
            server.awaitTermination();

        } catch (Exception exception) {
            log.error("Shutting down server, error message is: {}", exception.getMessage());
            server.shutdown();
        }
        return !server.isShutdown();
    }

    @Bean
    public Server grpcServer() {
        return ServerBuilder
                .forPort(grpcServerPort)
                .addService(userGrpcController)
                .build();
    }
}
