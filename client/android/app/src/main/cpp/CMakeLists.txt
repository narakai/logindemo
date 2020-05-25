cmake_minimum_required(VERSION 3.4.1)

#cmake version 3.10.2
#message(WARNING "cmake version:${CMAKE_MAJOR_VERSION}.${CMAKE_MINOR_VERSION}.${CMAKE_PATCH_VERSION}")

# 引入grpc源码，编译结果输出到GRPC_BUILD_DIR，我们后面依赖了grpc++和libprotobuf两个库
#message(WARNING "GRPC_SRC_DIR:${GRPC_SRC_DIR}")
set(GRPC_BUILD_DIR ${CMAKE_BINARY_DIR}/grpc_build_outputs/)
file(MAKE_DIRECTORY ${GRPC_BUILD_DIR})
add_subdirectory(${GRPC_SRC_DIR} ${GRPC_BUILD_DIR})

find_library(log-lib
        log)

# djinni相关代码生成djinni-lib库
file(GLOB djinni_sources
        ${DJINNI_SRC_DIR}/support-lib/jni/*.cpp
        djinni/*.cpp
        hello_world_impl.cpp)

include_directories(
        ${DJINNI_SRC_DIR}/support-lib/
        ${DJINNI_SRC_DIR}/support-lib/jni/
        djinni/
        hello_world_impl.hpp)

add_library(djinni-lib
        SHARED ${djinni_sources})


# proto相关代码生成proto_lib库
file(GLOB proto_sources
        protos/*.cc)

include_directories(
        protos/)

add_library(proto-lib
        SHARED  ${proto_sources})

target_link_libraries(proto-lib
        grpc++
        libprotobuf
        android
        log)


# 生成一个测试用的本地库grpc-helloworld
add_library(grpc-helloworld
        SHARED
        grpc-helloworld.cc)

target_include_directories(grpc-helloworld
        PRIVATE
        protos/)

# 将前面其他的库都链接到grpc-helloworld库中
target_link_libraries(grpc-helloworld
        djinni-lib
        proto-lib
        android
        ${log-lib})
