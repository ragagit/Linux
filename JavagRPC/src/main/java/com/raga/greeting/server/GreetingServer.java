package com.raga.greeting.server;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.File;
import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello World");

//        Server server = ServerBuilder.forPort(50051)
//                .addService(new GreetServiceImpl())
//                .build();
        // secure server
        Server server = ServerBuilder.forPort(50051)
                .addService(new GreetServiceImpl())
                .useTransportSecurity(
                        new File("ssl/server.crt"),
                        new File("ssl/server.pem")
                )
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
