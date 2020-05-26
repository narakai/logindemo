# logindemo
login demo project

开发环境：iMac macOS Mojave 10.14.6     
部署环境：腾讯云服务器 1核2G内存 Ubuntu 18.04

#### 1、获取代码

```
git clone https://github.com/hujiaweibujidao/logindemo.git
cd logindemo
git submodule update --init
```

#### 2、编译djinni

```
cd tools/djinni
src/build
```

#### 3、编译grpc

(1)在本地开发机上使用CMake编译grpc源码，得到protoc和grpc_cpp_plugin工具（客户端开发需要）

build: [https://github.com/grpc/grpc/blob/master/BUILDING.md#install-after-build](https://github.com/grpc/grpc/blob/master/BUILDING.md) 
install: [https://github.com/grpc/grpc/blob/master/BUILDING.md#install-after-build](https://github.com/grpc/grpc/blob/master/BUILDING.md#install-after-build)  

```
apt-get install build-essential autoconf libtool pkg-config automake libssl-dev cmake

cd tools/grpc
mkdir -p cmake/build
cd cmake/build
cmake ../..
make
```

(2)在腾讯云服务器上编译安装protobuf和grpc

grpc/cxx: [grpc/cxx image on docker hub](https://hub.docker.com/r/grpc/cxx/dockerfile)

```
echo "--- installing protobuf ---" && \
cd third_party/protobuf && \
    ./autogen.sh && ./configure --enable-shared && \
    make -j$(nproc) && make -j$(nproc) check && make install && make clean && ldconfig

echo "--- installing grpc ---" && \
    cd /var/local/git/grpc && \
    make -j$(nproc) && make install && make clean && ldconfig
```

#### 4、代码生成

利用前面编译djinni和grpc源码后得到的命令行工具去自动生成相关代码

```
cd client/interface
chmod +x run_djinni.sh
./run_djinni.sh

cd protos
chmod +x run_proto.sh
./run_proto.sh
```

#### 5、构建后台服务

```
```

#### 6、部署后台服务

```
```
