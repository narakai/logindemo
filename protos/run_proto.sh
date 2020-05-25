
#! /usr/bin/env bash

### Configuration

proto_file="helloworld.proto"

# get base directory
base_dir=$(cd "`dirname "0"`" && pwd)

# output directories for generated src
cpp_out="$base_dir/../client/android/app/src/main/cpp/protos"
grpc_out="$base_dir/../client/android/app/src/main/cpp/protos"

# clean generated src dirs
rm -rf $cpp_out
rm -rf $grpc_out
mkdir $cpp_out
mkdir $grpc_out

# execute the protoc command
../tools/grpc/cmake/build/third_party/protobuf/protoc \
   --cpp_out=$cpp_out \
   $proto_file

../tools/grpc/cmake/build/third_party/protobuf/protoc \
   --plugin=protoc-gen-grpc=../tools/grpc/cmake/build/grpc_cpp_plugin \
   --grpc_out=$grpc_out \
   $proto_file
