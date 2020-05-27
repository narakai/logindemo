// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from logindemo.djinni

#pragma once

#include <cstdint>
#include <memory>
#include <string>

namespace logindemo {

struct CommonReponse;

class HelloWorld {
public:
    virtual ~HelloWorld() {}

    static std::shared_ptr<HelloWorld> create(const std::string & host, int32_t port);

    virtual CommonReponse signup(const std::string & name, const std::string & password, const std::string & device) = 0;

    virtual CommonReponse login(const std::string & name, const std::string & password, const std::string & device) = 0;

    virtual CommonReponse logout(const std::string & token) = 0;

    virtual std::string sayHello(const std::string & msg) = 0;
};

}  // namespace logindemo
