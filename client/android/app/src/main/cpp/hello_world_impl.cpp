#include "hello_world_impl.hpp"
#include <string>
#include <grpc++/grpc++.h>
#include <jni.h>

#include "protos/helloworld.pb.h"
#include "protos/helloworld.grpc.pb.h"

using grpc::Channel;
using grpc::ClientContext;
using grpc::Server;
using grpc::ServerBuilder;
using grpc::ServerContext;
using grpc::Status;
using helloworld::Greeter;
using helloworld::HelloReply;
using helloworld::HelloRequest;

class GreeterClient {
public:
    GreeterClient(std::shared_ptr <Channel> channel) : stub_(Greeter::NewStub(channel)) {}

    // Assembles the client's payload, sends it and presents the response back from the server.
    std::string SayHello(const std::string &message) {
        HelloRequest request;
        request.set_name(message);

        // Container for the data we expect from the server.
        HelloReply reply;

        // Context for the client. It could be used to convey extra information to the server and/or tweak certain RPC behaviors.
        ClientContext context;
        // The actual RPC.
        Status status = stub_->SayHello(&context, request, &reply);

        if (status.ok()) {
            return reply.message();
        } else {
            return status.error_message();
        }
    }

private:
    std::unique_ptr <Greeter::Stub> stub_;
};

namespace logindemo {

    std::shared_ptr <HelloWorld> HelloWorld::create() {
        return std::make_shared<HelloWorldImpl>();
    }

    HelloWorldImpl::HelloWorldImpl() {

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

    /**
     * 这个方法是演示从客户端发送msg给服务器端，服务器端返回hello msg给客户端，验证grpc接入调通
     */
    std::string HelloWorldImpl::sayHello(const std::string &host, int32_t port, const std::string &message) {
        const int host_port_buf_size = 1024;
        char host_port[host_port_buf_size];
        snprintf(host_port, host_port_buf_size, "%s:%d", host.c_str(), port);

        GreeterClient greeter(grpc::CreateChannel(host_port, grpc::InsecureChannelCredentials()));
        std::string reply = greeter.SayHello(message);
        return reply;
    }

}