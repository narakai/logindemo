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
    public static final int ERROR_REFRESH_TOKEN_NOT_EXIST = -5;
    public static final int ERROR_REFRESH_TOKEN_NOT_VALID = -6;

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
     * 1、注册一个新账号
     * 2、用相同名字再次注册
     */
    @Override
    public void signup(LoginRequest request, StreamObserver<CommonResponse> responseObserver) {
        String name = request.getName();
        String password = request.getPassword();
        String device = request.getDevice();
        logger.info("signup, name: " + name + ", password: " + password + ", device: " + device);

        DataBaseHelper.getInstance().open();

        boolean isUserExist = DataBaseHelper.getInstance().isUserExist(name);
        if (!isUserExist) {
            String salt = Utils.generateSalt();
            String passwordHash = Utils.md5(password + salt);
            DataBaseHelper.getInstance().addNewUser(name, passwordHash, salt);

            String token = Utils.generateToken(name);
            DataBaseHelper.getInstance().addNewToken(name, device, token);

            CommonResponse response = CommonResponse.newBuilder().setCode(0).setMessage("signup success").setTokenInfo(TokenInfo.newBuilder().setToken(token).build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            CommonResponse response = CommonResponse.newBuilder().setCode(ERROR_SIGNUP_USER_EXIST).setMessage("user name already exists").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
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
     * 4、用手机B登录上面的账号，观察是否会被踢下线 TODO：暂未实现服务器端向客户端推送消息
     * 5、关闭手机A的应用，用手机B登录上面的账号，然后重启手机A的应用，观察是否会要求重新登录
     */
    @Override
    public void login(LoginRequest request, StreamObserver<CommonResponse> responseObserver) {
        String name = request.getName();
        String password = request.getPassword();
        String device = request.getDevice();
        logger.info("login, name: " + name + ", password: " + password + ", device: " + device);

        DataBaseHelper.getInstance().open();

        boolean isUserExist = DataBaseHelper.getInstance().isUserExist(name);
        if (!isUserExist) {
            CommonResponse response = CommonResponse.newBuilder().setCode(ERROR_LOGIN_USER_NOT_EXIST).setMessage("user not exist").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            boolean isPasswordCorrect = DataBaseHelper.getInstance().isPasswordCorrect(name, password);
            if (!isPasswordCorrect) {
                CommonResponse response = CommonResponse.newBuilder().setCode(ERROR_LOGIN_NAME_PASSWORD_WRONG).setMessage("user name or password is wrong").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                boolean isTokenExist = DataBaseHelper.getInstance().isTokenExistByUserName(name);
                if (!isTokenExist) {
                    String token = Utils.generateToken(name);
                    DataBaseHelper.getInstance().addNewToken(name, device, token);

                    CommonResponse response = CommonResponse.newBuilder().setCode(0).setMessage("login success").setTokenInfo(TokenInfo.newBuilder().setToken(token).build()).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                } else {//TODO：这种情况下可能需要将已经在线的设备强制退出，或者其他已登录的设备需要在下次启动时refreshToken之后重新登录
                    String token = Utils.generateToken(name);
                    DataBaseHelper.getInstance().updateToken(name, device, token, 1);

                    CommonResponse response = CommonResponse.newBuilder().setCode(0).setMessage("login success").setTokenInfo(TokenInfo.newBuilder().setToken(token).build()).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            }
        }

        DataBaseHelper.getInstance().close();
    }

    /**
     * 登出
     * 1、先查询token表中是否存在这个token，如果不存在，返回-4，提示token不存在
     * 2、如果存在，那么将token记录的status设置为0，表示当前没有设备登录
     * <p>
     * 自测：
     * 1、登录账号之后登出该账号
     */
    @Override
    public void logout(TokenInfo request, StreamObserver<CommonResponse> responseObserver) {
        String token = request.getToken();
        logger.info("logout, token: " + token);

        DataBaseHelper.getInstance().open();

        boolean isTokenExist = DataBaseHelper.getInstance().isTokenExistByToken(token);
        if (!isTokenExist) {
            CommonResponse response = CommonResponse.newBuilder().setCode(ERROR_LOGOUT_TOKEN_NOT_EXIST).setMessage("token not exist").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            DataBaseHelper.getInstance().invalidToken(token);

            CommonResponse response = CommonResponse.newBuilder().setCode(0).setMessage("logout success").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        DataBaseHelper.getInstance().close();
    }

    /**
     * 刷新token
     * 1、先查询token表中是否存在这个token，如果不存在，返回-5，提示token不存在
     * 2、如果存在，那么检查token的status状态，如果不满足status=1并且device一致，返回-6，提示用户重新登录
     * <p>
     * 自测：
     * 1、账号登录之后修改数据库中某个token记录的device或者status的值，观察应用重启是否需要这个账号重新登录
     */
    @Override
    public void refreshToken(RefreshTokenRequest request, StreamObserver<CommonResponse> responseObserver) {
        String token = request.getToken();
        String device = request.getDevice();
        logger.info("refreshToken, token: " + token + ", device: " + device);

        DataBaseHelper.getInstance().open();

        boolean isTokenExist = DataBaseHelper.getInstance().isTokenExistByToken(token);
        if (!isTokenExist) {
            CommonResponse response = CommonResponse.newBuilder().setCode(ERROR_REFRESH_TOKEN_NOT_EXIST).setMessage("token not exist").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            boolean isTokenValid = DataBaseHelper.getInstance().isTokenValid(token, device);
            if (!isTokenValid) {
                CommonResponse response = CommonResponse.newBuilder().setCode(ERROR_REFRESH_TOKEN_NOT_VALID).setMessage("token invalid, please login first").setTokenInfo(TokenInfo.newBuilder().setToken("").build()).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                CommonResponse response = CommonResponse.newBuilder().setCode(0).setMessage("refresh token success").setTokenInfo(TokenInfo.newBuilder().setToken(token).build()).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        }

        DataBaseHelper.getInstance().close();
    }
}
