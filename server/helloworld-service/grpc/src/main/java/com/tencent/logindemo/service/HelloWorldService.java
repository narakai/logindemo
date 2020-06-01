package com.tencent.logindemo.service;

import com.tencent.logindemo.database.DataBaseHelper;
import com.tencent.logindemo.proto.*;
import com.tencent.logindemo.util.Utils;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

/**
 * 处理客户端的注册、登录、登出和测试调通的rpc调用
 * <p>
 * TODO: 1、为了简单起见，SQL操作默认都是成功的
 * <p>
 * See https://grpc.io/docs/tutorials/basic/java/
 * See https://www.baeldung.com/grpc-introduction
 */
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {

    private static final Logger logger = Logger.getLogger(HelloWorldService.class.getName());

    /**
     * 声明一些错误码，表示不同情况下的错误原因
     * TODO：实际情况下要在proto中定义，保持客户端和服务器端对齐
     */
    public static final int ERROR_SIGNUP_USER_EXIST = -1;
    public static final int ERROR_LOGIN_USER_NOT_EXIST = -2;
    public static final int ERROR_LOGIN_NAME_PASSWORD_WRONG = -3;
    public static final int ERROR_LOGOUT_TOKEN_NOT_EXIST = -4;

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
     * 3、客户端参数需要进行检查，注册密码需要加盐加密再保存到数据库中，token的生成方式与device有关
     * <p>
     * 自测：
     * 1、注册新账号
     * 2、用相同名字再次注册
     */
    @Override
    public void signup(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        String name = request.getName();
        String password = request.getPassword();
        String device = request.getDevice();
        logger.info("signup, name: " + name + ", password: " + password + ", device: " + device);

        DataBaseHelper.getInstance().open();

        boolean isUserExist = DataBaseHelper.getInstance().isUserExist(name);
        if (!isUserExist) {
            String salt = Utils.generateSalt();
            DataBaseHelper.getInstance().addNewUser(name, password, salt);

            String token = Utils.generateToken(name);
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
     * 1、先查询user表是否有名为参数name的账户存在，如果不存在，返回-2，提示该用户不存在
     * 2、然后查询user表检查密码是否正确，如果不正确，返回-3，提示账号或者密码错误
     * 3、如果密码正确，那么查询token表中该用户的token，更新status和device
     * <p>
     * 自测：
     * 1、登录不存在的用户
     * 2、登录已有的账号，但是密码错误
     * 3、登录已有的账号，并且密码正确
     */
    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        String name = request.getName();
        String password = request.getPassword();
        String device = request.getDevice();
        logger.info("login, name: " + name + ", password: " + password + ", device: " + device);

        DataBaseHelper.getInstance().open();

        boolean isUserExist = DataBaseHelper.getInstance().isUserExist(name);
        if (!isUserExist) {
            LoginResponse response = LoginResponse.newBuilder().setCode(ERROR_LOGIN_USER_NOT_EXIST).setMessage("该用户不存在").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            boolean isPasswordCorrect = DataBaseHelper.getInstance().isPasswordCorrect(name, password);
            if (!isPasswordCorrect) {
                LoginResponse response = LoginResponse.newBuilder().setCode(ERROR_LOGIN_NAME_PASSWORD_WRONG).setMessage("账号或密码错误").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                boolean isTokenExist = DataBaseHelper.getInstance().isTokenExistByUserName(name);
                if (!isTokenExist) {
                    String token = Utils.generateToken(name);
                    DataBaseHelper.getInstance().addNewToken(name, device, token);

                    LoginResponse response = LoginResponse.newBuilder().setCode(0).setMessage("登录成功").setTokenInfo(TokenInfo.newBuilder().setToken(token).build()).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                } else {//TODO：这种情况下可能需要将已经在线的设备强制退出，或者其他已登录的设备需要在下次启动时refreshToken之后重新登录
                    String token = Utils.generateToken(name);
                    DataBaseHelper.getInstance().updateToken(name, device, token, 1);

                    LoginResponse response = LoginResponse.newBuilder().setCode(0).setMessage("登录成功").setTokenInfo(TokenInfo.newBuilder().setToken(token).build()).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            }
        }

        DataBaseHelper.getInstance().close();
    }

    /**
     * 登出
     */
    @Override
    public void logout(TokenInfo request, StreamObserver<LoginResponse> responseObserver) {
        String token = request.getToken();
        logger.info("logout, token: " + token);

        DataBaseHelper.getInstance().open();

        boolean isTokenExist = DataBaseHelper.getInstance().isTokenExistByToken(token);
        if (!isTokenExist) {
            LoginResponse response = LoginResponse.newBuilder().setCode(ERROR_LOGOUT_TOKEN_NOT_EXIST).setMessage("token不存在").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            DataBaseHelper.getInstance().invalidToken(token);//将token记录的status设置为0即可

            LoginResponse response = LoginResponse.newBuilder().setCode(0).setMessage("登出成功").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        DataBaseHelper.getInstance().close();
    }

    /**
     * 刷新token
     */
    @Override
    public void refreshToken(TokenInfo request, StreamObserver<LoginResponse> responseObserver) {
        super.refreshToken(request, responseObserver);
    }
}
