//package com.spring.grpc.grpcserver.configuration;
//
//import com.spring.grpc.grpcserver.controller.UserGrpcController;
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Slf4j
//@Configuration
//public class GrpcServerConfiguration {
//
//    private final Integer grpcServerPort;
//    private final UserGrpcController userGrpcController;
//
//    public GrpcServerConfiguration(
//            UserGrpcController userGrpcController,
//            @Value("${grpc.server.port}") Integer grpcServerPort) {
//
//        this.grpcServerPort = grpcServerPort;
//        this.userGrpcController = userGrpcController;
//    }
//
//    @Bean
//    public Server grpcServer() {
//        Server grpcServer = this.buildGrpcServer();
//        this.initGrpcServer(grpcServer);
//        return grpcServer;
//    }
//
//    private void initGrpcServer(Server grpcServer) {
//        try {
//            log.info("Starting grpc server...");
//            grpcServer.start();
//
//            log.info("Server listening at port {}...", grpcServerPort);
//            grpcServer.awaitTermination();
//
//        } catch (Exception exception) {
//            log.error("Shutting down server, error message is: {}", exception.getMessage());
//            grpcServer.shutdown();
//        }
//    }
//
//    private Server buildGrpcServer() {
//        return ServerBuilder
//                .forPort(grpcServerPort)
//                .addService(userGrpcController)
//                .build();
//    }
//}
