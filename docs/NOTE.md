
在云服务器上安装 docker compose

curl -L https://github.com/docker/compose/releases/download/1.25.3/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose





大致思路

1、djinni定义跨平台的接口，注册和登录

register(name, password, callback)

login(name, password, callback)


2、grpc实现客户端和服务端的网络请求，也就是在c++层处理上面的注册和登录

rpc register()

rpc login()

3、Android NDK + CMake + djinni + gRPC

djinni只有几个头文件和C++文件，比较容易整合进来

gRPC有很多文件，能输出很多个库，整合进来比较麻烦


添加子模块

```
git submodule add https://github.com/grpc/grpc tools/grpc


```

编译grpc 

[building](https://github.com/grpc/grpc/blob/master/BUILDING.md)

```
$ mkdir -p cmake/build
$ cd cmake/build
$ cmake ../..
$ make
```


### Ubuntu环境配置

```
export MY_INSTALL_DIR=$HOME/local
mkdir -p $MY_INSTALL_DIR
export PATH="$PATH:$MY_INSTALL_DIR/bin"

//这里安装的cmake版本是3.10，不适合gRPC
sudo apt install -y cmake

wget -q -O cmake-linux.sh https://github.com/Kitware/CMake/releases/download/v3.17.0/cmake-3.17.0-Linux-x86_64.sh
sh cmake-linux.sh -- --skip-license --prefix=$MY_INSTALL_DIR
rm cmake-linux.sh

sudo apt install -y build-essential autoconf libtool pkg-config
git clone --recurse-submodules -b v1.28.1 https://github.com/grpc/grpc
cd grpc


```


### 生成rpc代码

```
protoc --cpp_out=./ helloworld.proto
//run on Mac, grpc installed in /usr/local/bin
protoc --grpc_out=./ --plugin=protoc-gen-grpc=/usr/local/bin/grpc_cpp_plugin helloworld.proto
```


### 云服务器安装gRPC

#### Ubuntu上安装gRPC

```
sudo apt-get install autoconf automake libtool make g++ unzip
sudo apt-get install libgflags-dev libgtest-dev
sudo apt-get install clang libc++-dev

git clone https://github.com/grpc/grpc.git
cd grpc
git submodule update --init

//install gRPC
make
sudo make install 

//install protobuffer
cd third_party/protobuf/
git submodule update --init --recursive
./autogen.sh
./configure
make
make check
sudo make install 
```

#### 安装protobuf

```
//Mac
brew install gRPC
//install protobuf only
brew install protobuf

//Ubuntu
sudo apt install protobuf-compiler
```


