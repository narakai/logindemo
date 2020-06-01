package com.tencent.logindemo.service;

import com.tencent.logindemo.database.DataBaseHelper;
import com.tencent.logindemo.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.Random;
import java.util.logging.Logger;

/**
 * 处理客户端的注册、登录、登出和测试调通的rpc调用
 * <p>
 * See https://grpc.io/docs/tutorials/basic/java/
 * See https://www.baeldung.com/grpc-introduction
 */
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {

    private static final Logger logger = Logger.getLogger(HelloWorldService.class.getName());

    public static final int ERROR_SIGNUP_USER_EXIST = -1;

    /**
     * 测试和客户端的通信
     */
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        logger.info("sayHello, request: " + request.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    /**
     * 注册
     * 1、先查询user表是否有名为参数name的账户存在，如果存在，返回-1，提示该用户已经存在
     * 2、如果不存在，那么就创建账户，同时创建token插入到token表，返回0和token值
     */
    @Override
    public void signup(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        String name = request.getName();
        String password = request.getPassword();
        String device = request.getDevice();

        DataBaseHelper.getInstance().open();

        boolean isUserExist = DataBaseHelper.getInstance().isUserExist(name);
        if (!isUserExist) {
            String salt = "salt";
            DataBaseHelper.getInstance().addNewUser(name, password, salt);

            String token = "token#" + new Random().nextInt(100);
            DataBaseHelper.getInstance().addNewToken(name, device, token);

            LoginResponse response = LoginResponse.newBuilder().setCode(0).setMessage("注册成功").setTokenInfo(TokenInfo.newBuilder().setToken(token).build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            LoginResponse response = LoginResponse.newBuilder().setCode(ERROR_SIGNUP_USER_EXIST).setMessage("该用户已存在").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        DataBaseHelper.getInstance().close();
    }

    /**
     * 登录
     */
    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        super.login(request, responseObserver);
    }

    /**
     * 登出
     */
    @Override
    public void logout(TokenInfo request, StreamObserver<LoginResponse> responseObserver) {
        super.logout(request, responseObserver);
    }

    /**
     * 刷新token
     */
    @Override
    public void refreshToken(TokenInfo request, StreamObserver<LoginResponse> responseObserver) {
        super.refreshToken(request, responseObserver);
    }
}
