package com.raga.calculator.client;

import com.proto.calculator.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class CalculatorClient {

    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");

        CalculatorClient main = new CalculatorClient();
        main.run();
    }

    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        //doUnaryCall(channel);

        //doServerStreamingCall(channel);

        //doClientStreamingCall(channel);

        //doBidiStreamingCall(channel);

        doErrorCall(channel);

        System.out.println("Shutting down channel");
        channel.shutdown();

    }

    public void doUnaryCall(ManagedChannel channel){
        CalculatorServiceGrpc.CalculatorServiceBlockingStub calcClient = CalculatorServiceGrpc.newBlockingStub(channel);

        Calculator calculate = Calculator.newBuilder()
                .setA(7)
                .setB(7)
                .build();

        CalculatorRequest calculatorRequest = CalculatorRequest.newBuilder()
                .setCalculate(calculate)
                .build();

        CalculatorResponse calculateResponse = calcClient.calculate(calculatorRequest);

        System.out.println(calculateResponse.getResult());
    }

    private void doErrorCall(ManagedChannel channel) {
        CalculatorServiceGrpc.CalculatorServiceBlockingStub blockingStub = CalculatorServiceGrpc.newBlockingStub(channel);

        int number = -1;

        try {
            blockingStub.squareRoot(SquareRootRequest.newBuilder()
                    .setNumber(number)
                    .build());
        } catch (StatusRuntimeException e) {
            System.out.println("Got an exception for square root!");
            e.printStackTrace();
        }

    }

}
