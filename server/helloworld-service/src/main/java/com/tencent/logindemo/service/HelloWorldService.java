package com.tencent.logindemo.service;

import com.tencent.logindemo.database.DataBaseHelper;
import com.tencent.logindemo.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

/**
 * See https://grpc.io/docs/tutorials/basic/java/
 * See https://www.baeldung.com/grpc-introduction
 */
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {

    private static final Logger logger = Logger.getLogger(HelloWorldService.class.getName());

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        logger.info("sayHello, request: " + request.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void signup(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        String name = request.getName();
        String password = request.getPassword();
        String device = request.getDevice();

        boolean isUserExist = DataBaseHelper.getInstance().isUserExist(name);
        if (!isUserExist) {
            DataBaseHelper.getInstance().addNewUser(name, password, device);
        } else {
            LoginResponse response = LoginResponse.newBuilder().setCode(0).setMessage("OK").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
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
