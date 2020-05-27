#pragma once

#include "djinni/hello_world.hpp"
#include "protos/helloworld.pb.h"
#include "protos/helloworld.grpc.pb.h"

namespace logindemo {

    class HelloWorldImpl : public logindemo::HelloWorld {

    public:

        HelloWorldImpl(const std::string & host, int32_t port);

        std::string get_hello_world();

        std::string sayHello(const std::string & msg);

    private:

        std::unique_ptr <helloworld::Greeter::Stub> stub_;

    };

}