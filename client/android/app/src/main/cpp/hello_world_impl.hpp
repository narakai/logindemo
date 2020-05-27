#pragma once

#include "djinni/hello_world.hpp"
#include "djinni/common_reponse.hpp"
#include "protos/helloworld.pb.h"
#include "protos/helloworld.grpc.pb.h"

namespace logindemo {

    class HelloWorldImpl : public logindemo::HelloWorld {

    public:

        HelloWorldImpl(const std::string & host, int32_t port);

        CommonReponse signup(const std::string & name, const std::string & password, const std::string & device);

        CommonReponse login(const std::string & name, const std::string & password, const std::string & device);

        CommonReponse logout(const std::string & token);

        std::string sayHello(const std::string & msg);

    private:

        std::unique_ptr <helloworld::Greeter::Stub> stub_;

    };

}