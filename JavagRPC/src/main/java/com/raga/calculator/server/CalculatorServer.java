package com.raga.calculator.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;

public class CalculatorServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Calculator Service");

        Server server = ServerBuilder.forPort(50052)
                .addService(new CalculatorServiceImpl())
                .addService(ProtoReflectionService.newInstance())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook( new Thread( () -> {
            System.out.println("Shutdown received");
            server.shutdown();
            System.out.println("Successfully stopped");
        }));

        //We need to block the main thread otherwise it will exit
        server.awaitTermination();


    }
}
