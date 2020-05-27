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

    // Assembles the client's payload, sends it and presents the response back
    // from the server.
    std::string SayHello(const std::string &user) {
        // Data we are sending to the server.
        HelloRequest request;
        request.set_name(user);

        // Container for the data we expect from the server.
        HelloReply reply;

        // Context for the client. It could be used to convey extra information to
        // the server and/or tweak certain RPC behaviors.
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

extern "C" JNIEXPORT jstring
JNICALL
Java_com_tencent_logindemo_activity_MainActivity_sayHello(
        JNIEnv *env, jobject obj_unused, jstring host_raw, jint port_raw,
        jstring message_raw) {
    const char *host_chars = env->GetStringUTFChars(host_raw, (jboolean *) 0);
    std::string host(host_chars, env->GetStringUTFLength(host_raw));

    int port = static_cast<int>(port_raw);

    const char *message_chars = env->GetStringUTFChars(message_raw, (jboolean *) 0);
    std::string message(message_chars, env->GetStringUTFLength(message_raw));

    const int host_port_buf_size = 1024;
    char host_port[host_port_buf_size];
    snprintf(host_port, host_port_buf_size, "%s:%d", host.c_str(), port);

    GreeterClient greeter(
            grpc::CreateChannel(host_port, grpc::InsecureChannelCredentials()));
    std::string reply = greeter.SayHello(message);

    return env->NewStringUTF(reply.c_str());
    //return env->NewStringUTF(message.c_str());
}

namespace logindemo {

    std::shared_ptr <HelloWorld> HelloWorld::create() {
        return std::make_shared<HelloWorldImpl>();
    }

    HelloWorldImpl::HelloWorldImpl() {

    }

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