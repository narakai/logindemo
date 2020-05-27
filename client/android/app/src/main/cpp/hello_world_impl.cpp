#include "hello_world_impl.hpp"
#include <string>
#include <grpc++/grpc++.h>
#include <jni.h>

using grpc::Channel;
using grpc::ClientContext;
using grpc::Server;
using grpc::ServerBuilder;
using grpc::ServerContext;
using grpc::Status;
using helloworld::Greeter;
using helloworld::HelloReply;
using helloworld::HelloRequest;
using helloworld::LoginRequest;
using helloworld::LoginResponse;
using helloworld::TokenInfo;

namespace logindemo {

    std::shared_ptr<HelloWorld> HelloWorld::create(const std::string &host, int32_t port) {
        return std::make_shared<HelloWorldImpl>(host, port);
    }

    HelloWorldImpl::HelloWorldImpl(const std::string &host, int32_t port) {
        const int host_port_buf_size = 1024;
        char host_port[host_port_buf_size];
        snprintf(host_port, host_port_buf_size, "%s:%d", host.c_str(), port);
        stub_ = Greeter::NewStub(
                grpc::CreateChannel(host_port, grpc::InsecureChannelCredentials()));
    }

    CommonReponse HelloWorldImpl::signup(const std::string &name, const std::string &password,
                                       const std::string &device) {
        LoginRequest request;
        request.set_name(name);
        request.set_password(password);
        request.set_device(device);

        LoginResponse response;
        ClientContext context;
        Status status = stub_->Signup(&context, request, &response);

        if (status.ok()) {
            return CommonReponse(response.code(), response.message(), response.tokeninfo().token());
        } else {
            return CommonReponse(status.error_code(), status.error_message(), status.error_details());
        }
    }

    CommonReponse HelloWorldImpl::login(const std::string &name, const std::string &password,
                                      const std::string &device) {
        LoginRequest request;
        request.set_name(name);
        request.set_password(password);
        request.set_device(device);

        LoginResponse response;
        ClientContext context;
        Status status = stub_->Login(&context, request, &response);

        if (status.ok()) {
            return CommonReponse(response.code(), response.message(), response.tokeninfo().token());
        } else {
            return CommonReponse(status.error_code(), status.error_message(), status.error_details());
        }
    }

    CommonReponse HelloWorldImpl::logout(const std::string &token) {
        TokenInfo request;
        request.set_token(token);

        LoginResponse response;
        ClientContext context;
        Status status = stub_->Logout(&context, request, &response);

        if (status.ok()) {
            return CommonReponse(response.code(), response.message(), response.tokeninfo().token());
        } else {
            return CommonReponse(status.error_code(), status.error_message(), status.error_details());
        }
    }

    /**
     * 这个方法是演示从客户端发送msg给服务器端，服务器端返回hello msg给客户端，验证grpc接入调通
     */
    std::string HelloWorldImpl::sayHello(const std::string &message) {
        HelloRequest request;
        request.set_name(message);

        HelloReply reply;
        ClientContext context;
        Status status = stub_->SayHello(&context, request, &reply);

        if (status.ok()) {
            return reply.message();
        } else {
            return status.error_message();
        }
    }

}