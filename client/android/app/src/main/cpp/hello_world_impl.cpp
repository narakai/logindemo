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

namespace logindemo {

    std::shared_ptr <HelloWorld> HelloWorld::create(const std::string & host, int32_t port) {
        return std::make_shared<HelloWorldImpl>(host, port);
    }

    HelloWorldImpl::HelloWorldImpl(const std::string & host, int32_t port) {
        const int host_port_buf_size = 1024;
        char host_port[host_port_buf_size];
        snprintf(host_port, host_port_buf_size, "%s:%d", host.c_str(), port);
        stub_ = Greeter::NewStub(grpc::CreateChannel(host_port, grpc::InsecureChannelCredentials()));
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

    /**
     * 这个方法是演示从native层返回字符串给上层，验证djinni接入调通
     */
    std::string HelloWorldImpl::get_hello_world() {
        std::string myString = "Hello World! ";
        time_t t = time(0);
        tm now = *localtime(&t);
        char tmdescr[200] = {0};
        const char fmt[] = "%r";
        if (strftime(tmdescr, sizeof(tmdescr) - 1, fmt, &now) > 0) {
            myString += tmdescr;
        }
        return myString;
    }

}