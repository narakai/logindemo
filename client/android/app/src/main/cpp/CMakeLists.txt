cmake_minimum_required(VERSION 3.4.1)

#cmake version 3.10.2
#message(WARNING "cmake version:${CMAKE_MAJOR_VERSION}.${CMAKE_MINOR_VERSION}.${CMAKE_PATCH_VERSION}")

# 引入grpc源码，编译结果输出到GRPC_BUILD_DIR
#message(WARNING "GRPC_SRC_DIR:${GRPC_SRC_DIR}")
set(GRPC_BUILD_DIR ${CMAKE_BINARY_DIR}/grpc_build_outputs/)
file(MAKE_DIRECTORY ${GRPC_BUILD_DIR})
add_subdirectory(${GRPC_SRC_DIR} ${GRPC_BUILD_DIR})

# 指定由proto生成的文件的存放路径，并将它作为头文件搜索路径
set(GRPC_PROTO_GENS_DIR ${CMAKE_BINARY_DIR}/gens)
file(MAKE_DIRECTORY ${GRPC_PROTO_GENS_DIR})
include_directories(${GRPC_PROTO_GENS_DIR})

# 定义函数处理proto文件，生成pb头文件和grpc文件
function(android_protobuf_grpc_generate_cpp SRC_FILES HDR_FILES INCLUDE_ROOT)
    if(NOT ARGN)
        message(SEND_ERROR "Error: android_protobuf_grpc_generate_cpp() called without any proto files")
        return()
    endif()

    set(${SRC_FILES})
    set(${HDR_FILES})
    set(PROTOBUF_INCLUDE_PATH -I ${INCLUDE_ROOT})
    foreach(FIL ${ARGN})
        get_filename_component(ABS_FIL ${FIL} ABSOLUTE)
        get_filename_component(FIL_WE ${FIL} NAME_WE)
        file(RELATIVE_PATH REL_FIL ${CMAKE_CURRENT_SOURCE_DIR}/${INCLUDE_ROOT} ${ABS_FIL})
        get_filename_component(REL_DIR ${REL_FIL} DIRECTORY)
        set(RELFIL_WE "${REL_DIR}/${FIL_WE}")

        list(APPEND ${SRC_FILES} "${GRPC_PROTO_GENS_DIR}/${RELFIL_WE}.pb.cc")
        list(APPEND ${HDR_FILES} "${GRPC_PROTO_GENS_DIR}/${RELFIL_WE}.pb.h")
        list(APPEND ${SRC_FILES} "${GRPC_PROTO_GENS_DIR}/${RELFIL_WE}.grpc.pb.cc")
        list(APPEND ${HDR_FILES} "${GRPC_PROTO_GENS_DIR}/${RELFIL_WE}.grpc.pb.h")

        add_custom_command(
                OUTPUT "${GRPC_PROTO_GENS_DIR}/${RELFIL_WE}.grpc.pb.cc"
                "${GRPC_PROTO_GENS_DIR}/${RELFIL_WE}.grpc.pb.h"
                "${GRPC_PROTO_GENS_DIR}/${RELFIL_WE}.pb.cc"
                "${GRPC_PROTO_GENS_DIR}/${RELFIL_WE}.pb.h"
                COMMAND ${PROTOBUF_PROTOC_EXECUTABLE}
                ARGS --grpc_out=${GRPC_PROTO_GENS_DIR}
                --cpp_out=${GRPC_PROTO_GENS_DIR}
                --plugin=protoc-gen-grpc=${GRPC_CPP_PLUGIN_EXECUTABLE}
                ${PROTOBUF_INCLUDE_PATH}
                ${REL_FIL}
                WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}
                DEPENDS ${PROTOBUF_PROTOC_EXECUTABLE} ${GRPC_CPP_PLUGIN_EXECUTABLE} ${ABS_FIL} )
    endforeach()

    set_source_files_properties(${${SRC_FILES}} ${${HDR_FILES}} PROPERTIES GENERATED TRUE)
    set(${SRC_FILES} ${${SRC_FILES}} PARENT_SCOPE)
    set(${HDR_FILES} ${${HDR_FILES}} PARENT_SCOPE)
endfunction()

# 调用上面的函数，参数传入测试用的helloworld.proto文件
android_protobuf_grpc_generate_cpp(
        HELLOWORLD_PROTO_SRCS HELLOWORLD_PROTO_HDRS ${PROTOS_SRC_DIR} ${PROTOS_SRC_DIR}/helloworld.proto)

# proto相关代码生成proto_lib库
add_library(proto-lib
        SHARED ${HELLOWORLD_PROTO_HDRS} ${HELLOWORLD_PROTO_SRCS})

target_link_libraries(proto-lib
        grpc++
        libprotobuf
        android
        log)

# 添加djinni生成的公共C++代码
file(GLOB djinni_sources
        ${DJINNI_SRC_DIR}/support-lib/jni/*.cpp
        djinni/*.cpp
        *.cpp)

# 添加一些头文件所在的目录
include_directories(
        ${DJINNI_SRC_DIR}/support-lib/
        ${DJINNI_SRC_DIR}/support-lib/jni/
        djinni/cpp/
        cpp/)

find_library(log-lib
        log)

# djinni相关代码生成djinni-lib库
add_library(djinni-lib
        SHARED ${djinni_sources})

# 生成一个测试用的本地库grpc-helloworld
add_library(grpc-helloworld
        SHARED grpc-helloworld.cc)

target_include_directories(grpc-helloworld
        PRIVATE ${HELLOWORLD_PROTO_HEADERS})

# 将前面其他的库都链接到grpc-helloworld库中
target_link_libraries(grpc-helloworld
        djinni-lib
        proto-lib
        android
        ${log-lib})
