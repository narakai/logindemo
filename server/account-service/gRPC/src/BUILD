package(default_visibility = ["//visibility:public"])

load("@rules_proto//proto:defs.bzl", "proto_library")
load("@rules_cc//cc:defs.bzl", "cc_binary", "cc_proto_library")
load("@com_github_grpc_grpc//bazel:cc_grpc_library.bzl", "cc_grpc_library")

proto_library(
    name = "helloworld_proto",
    srcs = ["helloworld.proto"],
)

cc_proto_library(
    name = "helloworld_cc_proto",
    deps = [":helloworld_proto"],
)

##### 一定要有grpc_only = True
cc_grpc_library(
    name = "helloworld_cc_grpc",
    srcs = [":helloworld_proto"],
    deps = [":helloworld_cc_proto"],
    grpc_only = True,
)

cc_library(
    name = "Md5Util",
    srcs = ["Md5.cc"],
    hdrs = ["Md5.h"],
)

cc_library(
    name = "mysqlDriver",
    srcs = ["libs/libmysqlclient.so","libs/libmysqlclient_r.so"],
    hdrs = glob(["include/**/*.h"]),
)

cc_library(
    name = "mysqlpool",
    srcs = ["mysqlpool.cc"],
    hdrs = ["mysqlpool.h"],
    deps = [":mysqlDriver"],
    copts = ["-Isrc/include"]
)

cc_binary(
    name = "greeter-server",
    srcs = ["server.cc"],
    defines = ["BAZEL_BUILD"],
    deps = [
        "@com_github_grpc_grpc//:grpc++",
        ":helloworld_cc_grpc",
        ":mysqlpool",
    ]
)
