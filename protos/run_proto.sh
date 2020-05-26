
#! /usr/bin/env bash

######### 实际开发可以只用生成到当前目录，然后同步给客户端和服务器端 #########

### Configuration

proto_file="helloworld.proto"

# get base directory
base_dir=$(cd "`dirname "0"`" && pwd)

######### 生成到客户端 #########

# output directories for generated src
cpp_out="$base_dir/../client/android/app/src/main/cpp/protos"
grpc_out="$base_dir/../client/android/app/src/main/cpp/protos"

# clean generated src dirs
rm -rf $cpp_out
rm -rf $grpc_out
mkdir $cpp_out
mkdir $grpc_out

# execute the protoc command
/usr/local/bin/protoc \
   --cpp_out=$cpp_out \
   $proto_file

/usr/local/bin/protoc \
   --plugin=protoc-gen-grpc=../tools/grpc/cmake/build/grpc_cpp_plugin \
   --grpc_out=$grpc_out \
   $proto_file
