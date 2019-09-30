package com.raga.calculator.server;

import com.proto.calculator.*;
//import com.proto.calculator.CalculatorServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {


    @Override
    public void calculate(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
        //super.calculate(request, responseObserver);
        Calculator calculator = request.getCalculate();
        int a = calculator.getA();
        int b = calculator.getB();

        int result = a + b;

        CalculatorResponse response = CalculatorResponse.newBuilder()
                .setResult(result)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void squareRoot(SquareRootRequest request, StreamObserver<SquareRootResponse> responseObserver) {
        Integer number = request.getNumber();

        if (number >= 0) {
            double numberRoot = Math.sqrt(number);
            responseObserver.onNext(
                    SquareRootResponse.newBuilder()
                            .setNumberRoot(numberRoot)
                            .build()
            );
            responseObserver.onCompleted();
        } else {
            // we construct the exception
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("The number being sent is not positive")
                            .augmentDescription("Number sent: " + number)
                            .asRuntimeException()
            );
        }

    }
}
