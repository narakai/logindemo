
#! /usr/bin/env bash

######### 实际开发可以只用生成到当前目录，然后同步给客户端和服务器端 #########

### Configuration

proto_file="helloworld.proto"

# get base directory
base_dir=$(cd "`dirname "0"`" && pwd)

######### 生成客户端代码 #########

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

######### 生成服务器端代码 => 改为在代码中生成代码 #########

# java_out="$base_dir/../server/helloworld-service/src/main/java"

# rm -rf $java_out
# mkdir $java_out

# /usr/local/bin/protoc \
#    --java_out=$java_out \
#    $proto_file

# grpc-java codegen需要先下载对应grpc-java工具
# https://github.com/grpc/grpc-java/tree/master/compiler
# /usr/local/bin/protoc \
#    --plugin=protoc-gen-grpc-java=../tools/protoc/protoc-gen-grpc-java-1.29.0-osx-x86_64.exe
#    --grpc-java_out=$java_out \
#    --proto_path=$proto_file \
#    $proto_file
