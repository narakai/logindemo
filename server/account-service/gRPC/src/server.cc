/*
 *
 * Copyright 2019 The Cloud Robotics Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

#include <csignal>
#include <future>
#include <iostream>
#include <memory>
#include <string>
#include <thread>
#include <grpcpp/grpcpp.h>

#include "src/helloworld.grpc.pb.h"
#include "src/mysqlpool.h"

#define  MYSQL_ADDRESS "mysql"
#define  MYSQL_USRNAME "javayhu"
#define  MYSQL_USRPASSWORD "javayhu"
#define  MYSQL_USEDB "logindemo"
#define  MYSQL_PORT 3306
#define  MYSQL_MAX_CONNECTCOUNT 30

using grpc::Server;
using grpc::ServerBuilder;
using grpc::ServerContext;
using grpc::Status;
using helloworld::Greeter;
using helloworld::HelloReply;
using helloworld::HelloRequest;

MysqlPool* mysql;

// Logic and data behind the server's behavior.
class GreeterServiceImpl final : public Greeter::Service {
  Status SayHello(ServerContext* context, const HelloRequest* request, HelloReply* reply) override {
    std::cout << "Received request: " << request->ShortDebugString() << std::endl;
    std::string prefix("Hello ");
    reply->set_message(prefix + request->name());

    MYSQL* connection = mysql->getOneConnect();

    std::string insertAccountSql("INSERT INTO account(name, password) VALUES('" + request->ShortDebugString() + "', '666')");
    mysql_query(connection, insertAccountSql.c_str());
    mysql->close(connection);

    return Status::OK;
  }
};

void RunServer() {
  std::string server_address("0.0.0.0:50051");
  GreeterServiceImpl service;

  ServerBuilder builder;
  // Listen on the given address without any authentication mechanism.
  builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
  // Register "service" as the instance through which we'll communicate with
  // clients. In this case it corresponds to a *synchronous* service.
  builder.RegisterService(&service);
  // Finally assemble the server.
  std::unique_ptr<Server> server(builder.BuildAndStart());
  std::cout << "Server listening on " << server_address << std::endl;

  // Wait for the server to shutdown.
  server->Wait();
}

int main(int argc, char** argv) {
  mysql = MysqlPool::getMysqlPoolObject();
  mysql->setParameter(MYSQL_ADDRESS,MYSQL_USRNAME,MYSQL_USRPASSWORD,MYSQL_USEDB,MYSQL_PORT,NULL,0,MYSQL_MAX_CONNECTCOUNT);

  RunServer();

  delete mysql;

  return 0;
}
