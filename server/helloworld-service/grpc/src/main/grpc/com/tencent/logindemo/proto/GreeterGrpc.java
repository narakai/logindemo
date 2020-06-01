package com.tencent.logindemo.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: helloworld.proto")
public final class GreeterGrpc {

  private GreeterGrpc() {}

  public static final String SERVICE_NAME = "helloworld.Greeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tencent.logindemo.proto.HelloRequest,
      com.tencent.logindemo.proto.HelloReply> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = com.tencent.logindemo.proto.HelloRequest.class,
      responseType = com.tencent.logindemo.proto.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tencent.logindemo.proto.HelloRequest,
      com.tencent.logindemo.proto.HelloReply> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.tencent.logindemo.proto.HelloRequest, com.tencent.logindemo.proto.HelloReply> getSayHelloMethod;
    if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
          GreeterGrpc.getSayHelloMethod = getSayHelloMethod =
              io.grpc.MethodDescriptor.<com.tencent.logindemo.proto.HelloRequest, com.tencent.logindemo.proto.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.HelloReply.getDefaultInstance()))
              .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SayHello"))
              .build();
        }
      }
    }
    return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tencent.logindemo.proto.LoginRequest,
      com.tencent.logindemo.proto.LoginResponse> getSignupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Signup",
      requestType = com.tencent.logindemo.proto.LoginRequest.class,
      responseType = com.tencent.logindemo.proto.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tencent.logindemo.proto.LoginRequest,
      com.tencent.logindemo.proto.LoginResponse> getSignupMethod() {
    io.grpc.MethodDescriptor<com.tencent.logindemo.proto.LoginRequest, com.tencent.logindemo.proto.LoginResponse> getSignupMethod;
    if ((getSignupMethod = GreeterGrpc.getSignupMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSignupMethod = GreeterGrpc.getSignupMethod) == null) {
          GreeterGrpc.getSignupMethod = getSignupMethod =
              io.grpc.MethodDescriptor.<com.tencent.logindemo.proto.LoginRequest, com.tencent.logindemo.proto.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Signup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("Signup"))
              .build();
        }
      }
    }
    return getSignupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tencent.logindemo.proto.LoginRequest,
      com.tencent.logindemo.proto.LoginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = com.tencent.logindemo.proto.LoginRequest.class,
      responseType = com.tencent.logindemo.proto.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tencent.logindemo.proto.LoginRequest,
      com.tencent.logindemo.proto.LoginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<com.tencent.logindemo.proto.LoginRequest, com.tencent.logindemo.proto.LoginResponse> getLoginMethod;
    if ((getLoginMethod = GreeterGrpc.getLoginMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getLoginMethod = GreeterGrpc.getLoginMethod) == null) {
          GreeterGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.tencent.logindemo.proto.LoginRequest, com.tencent.logindemo.proto.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tencent.logindemo.proto.TokenInfo,
      com.tencent.logindemo.proto.LoginResponse> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Logout",
      requestType = com.tencent.logindemo.proto.TokenInfo.class,
      responseType = com.tencent.logindemo.proto.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tencent.logindemo.proto.TokenInfo,
      com.tencent.logindemo.proto.LoginResponse> getLogoutMethod() {
    io.grpc.MethodDescriptor<com.tencent.logindemo.proto.TokenInfo, com.tencent.logindemo.proto.LoginResponse> getLogoutMethod;
    if ((getLogoutMethod = GreeterGrpc.getLogoutMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getLogoutMethod = GreeterGrpc.getLogoutMethod) == null) {
          GreeterGrpc.getLogoutMethod = getLogoutMethod =
              io.grpc.MethodDescriptor.<com.tencent.logindemo.proto.TokenInfo, com.tencent.logindemo.proto.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.TokenInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("Logout"))
              .build();
        }
      }
    }
    return getLogoutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tencent.logindemo.proto.TokenInfo,
      com.tencent.logindemo.proto.LoginResponse> getRefreshTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RefreshToken",
      requestType = com.tencent.logindemo.proto.TokenInfo.class,
      responseType = com.tencent.logindemo.proto.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tencent.logindemo.proto.TokenInfo,
      com.tencent.logindemo.proto.LoginResponse> getRefreshTokenMethod() {
    io.grpc.MethodDescriptor<com.tencent.logindemo.proto.TokenInfo, com.tencent.logindemo.proto.LoginResponse> getRefreshTokenMethod;
    if ((getRefreshTokenMethod = GreeterGrpc.getRefreshTokenMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getRefreshTokenMethod = GreeterGrpc.getRefreshTokenMethod) == null) {
          GreeterGrpc.getRefreshTokenMethod = getRefreshTokenMethod =
              io.grpc.MethodDescriptor.<com.tencent.logindemo.proto.TokenInfo, com.tencent.logindemo.proto.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RefreshToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.TokenInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tencent.logindemo.proto.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("RefreshToken"))
              .build();
        }
      }
    }
    return getRefreshTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreeterStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreeterStub>() {
        @java.lang.Override
        public GreeterStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreeterStub(channel, callOptions);
        }
      };
    return GreeterStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreeterBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreeterBlockingStub>() {
        @java.lang.Override
        public GreeterBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreeterBlockingStub(channel, callOptions);
        }
      };
    return GreeterBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreeterFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreeterFutureStub>() {
        @java.lang.Override
        public GreeterFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreeterFutureStub(channel, callOptions);
        }
      };
    return GreeterFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GreeterImplBase implements io.grpc.BindableService {

    /**
     */
    public void sayHello(com.tencent.logindemo.proto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     */
    public void signup(com.tencent.logindemo.proto.LoginRequest request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignupMethod(), responseObserver);
    }

    /**
     */
    public void login(com.tencent.logindemo.proto.LoginRequest request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void logout(com.tencent.logindemo.proto.TokenInfo request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    /**
     */
    public void refreshToken(com.tencent.logindemo.proto.TokenInfo request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRefreshTokenMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tencent.logindemo.proto.HelloRequest,
                com.tencent.logindemo.proto.HelloReply>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            getSignupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tencent.logindemo.proto.LoginRequest,
                com.tencent.logindemo.proto.LoginResponse>(
                  this, METHODID_SIGNUP)))
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tencent.logindemo.proto.LoginRequest,
                com.tencent.logindemo.proto.LoginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tencent.logindemo.proto.TokenInfo,
                com.tencent.logindemo.proto.LoginResponse>(
                  this, METHODID_LOGOUT)))
          .addMethod(
            getRefreshTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tencent.logindemo.proto.TokenInfo,
                com.tencent.logindemo.proto.LoginResponse>(
                  this, METHODID_REFRESH_TOKEN)))
          .build();
    }
  }

  /**
   */
  public static final class GreeterStub extends io.grpc.stub.AbstractAsyncStub<GreeterStub> {
    private GreeterStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    /**
     */
    public void sayHello(com.tencent.logindemo.proto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void signup(com.tencent.logindemo.proto.LoginRequest request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void login(com.tencent.logindemo.proto.LoginRequest request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(com.tencent.logindemo.proto.TokenInfo request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void refreshToken(com.tencent.logindemo.proto.TokenInfo request,
        io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRefreshTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GreeterBlockingStub extends io.grpc.stub.AbstractBlockingStub<GreeterBlockingStub> {
    private GreeterBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.tencent.logindemo.proto.HelloReply sayHello(com.tencent.logindemo.proto.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tencent.logindemo.proto.LoginResponse signup(com.tencent.logindemo.proto.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignupMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tencent.logindemo.proto.LoginResponse login(com.tencent.logindemo.proto.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tencent.logindemo.proto.LoginResponse logout(com.tencent.logindemo.proto.TokenInfo request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tencent.logindemo.proto.LoginResponse refreshToken(com.tencent.logindemo.proto.TokenInfo request) {
      return blockingUnaryCall(
          getChannel(), getRefreshTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreeterFutureStub extends io.grpc.stub.AbstractFutureStub<GreeterFutureStub> {
    private GreeterFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tencent.logindemo.proto.HelloReply> sayHello(
        com.tencent.logindemo.proto.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tencent.logindemo.proto.LoginResponse> signup(
        com.tencent.logindemo.proto.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignupMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tencent.logindemo.proto.LoginResponse> login(
        com.tencent.logindemo.proto.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tencent.logindemo.proto.LoginResponse> logout(
        com.tencent.logindemo.proto.TokenInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tencent.logindemo.proto.LoginResponse> refreshToken(
        com.tencent.logindemo.proto.TokenInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getRefreshTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SIGNUP = 1;
  private static final int METHODID_LOGIN = 2;
  private static final int METHODID_LOGOUT = 3;
  private static final int METHODID_REFRESH_TOKEN = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.tencent.logindemo.proto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.HelloReply>) responseObserver);
          break;
        case METHODID_SIGNUP:
          serviceImpl.signup((com.tencent.logindemo.proto.LoginRequest) request,
              (io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse>) responseObserver);
          break;
        case METHODID_LOGIN:
          serviceImpl.login((com.tencent.logindemo.proto.LoginRequest) request,
              (io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((com.tencent.logindemo.proto.TokenInfo) request,
              (io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse>) responseObserver);
          break;
        case METHODID_REFRESH_TOKEN:
          serviceImpl.refreshToken((com.tencent.logindemo.proto.TokenInfo) request,
              (io.grpc.stub.StreamObserver<com.tencent.logindemo.proto.LoginResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tencent.logindemo.proto.HelloWorldProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Greeter");
    }
  }

  private static final class GreeterFileDescriptorSupplier
      extends GreeterBaseDescriptorSupplier {
    GreeterFileDescriptorSupplier() {}
  }

  private static final class GreeterMethodDescriptorSupplier
      extends GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreeterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getSignupMethod())
              .addMethod(getLoginMethod())
              .addMethod(getLogoutMethod())
              .addMethod(getRefreshTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
