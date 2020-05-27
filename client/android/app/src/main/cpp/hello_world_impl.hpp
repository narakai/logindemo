#pragma once

#include "djinni/hello_world.hpp"

namespace logindemo {

    class HelloWorldImpl : public logindemo::HelloWorld {

    public:

        HelloWorldImpl();

        std::string get_hello_world();

    };

}