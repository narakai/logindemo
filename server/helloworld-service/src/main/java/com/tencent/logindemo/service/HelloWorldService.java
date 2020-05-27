package com.tencent.logindemo.service;

import com.tencent.logindemo.proto.*;
import io.grpc.stub.StreamObserver;

/**
 * See https://grpc.io/docs/tutorials/basic/java/
 * See https://www.baeldung.com/grpc-introduction
 */
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {

    public static final String TAG = "HelloWorldService";

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        //super.sayHello(request, responseObserver);
    }

    @Override
    public void signup(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        super.signup(request, responseObserver);
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        super.login(request, responseObserver);
    }

    @Override
    public void logout(TokenInfo request, StreamObserver<LoginResponse> responseObserver) {
        super.logout(request, responseObserver);
    }

}
