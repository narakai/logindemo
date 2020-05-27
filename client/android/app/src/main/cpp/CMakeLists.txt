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

# 相关代码生成grpc-helloworld库
file(GLOB all_sources
        ${DJINNI_SRC_DIR}/support-lib/jni/*.cpp
        protos/*.cc
        djinni/*.cpp
        hello_world_impl.cpp)

include_directories(
        ${DJINNI_SRC_DIR}/support-lib/
        ${DJINNI_SRC_DIR}/support-lib/jni/
        djinni/
        protos/
        hello_world_impl.hpp)

add_library(native-lib
        SHARED ${all_sources})

target_link_libraries(native-lib
        grpc++
        libprotobuf
        android
        ${log-lib})
