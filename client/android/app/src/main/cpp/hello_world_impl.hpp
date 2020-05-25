#pragma once

#include "djinni/hello_world.hpp"

namespace logindemo {

    class HelloWorldImpl : public logindemo::HelloWorld {

    public:

        // Constructor
        HelloWorldImpl();

        // Our method that returns a string
        std::string get_hello_world();

    };

}