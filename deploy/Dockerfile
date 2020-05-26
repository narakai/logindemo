FROM ubuntu:18.04

MAINTAINER javayhu@tencent.com

# 修改apt源提高apt安装软件的速度
COPY sources.list sources.list
RUN mv /etc/apt/sources.list /etc/apt/sources.list.bak && mv sources.list /etc/apt/

# 安装一些必要的依赖工具
RUN apt-get update && apt-get install -y \
  build-essential autoconf git pkg-config \
  automake libtool curl make g++ unzip \
  libssl-dev cmake curl

# 下载grpc源码并进行编译
RUN curl -OL http://video.pocketpoetry.club/grpc-de195d3520a803fd6424bc33f154ba265af98bbe.tar.gz
RUN mkdir -p /var/local/git/ && tar -zxf grpc-de195d3520a803fd6424bc33f154ba265af98bbe.tar.gz -C /var/local/git/

# RUN echo "--- installing protobuf ---" && \
#     cd /var/local/git/grpc-de195d3520a803fd6424bc33f154ba265af98bbe/third_party/protobuf && \
#     ./autogen.sh && ./configure --enable-shared && \
#     make && make install && make clean && ldconfig
    
# RUN echo "--- installing grpc ---" && \
#     cd /var/local/git/grpc-de195d3520a803fd6424bc33f154ba265af98bbe && \
#     make && make install && make clean && ldconfig

RUN echo "--- building & installing grpc ---" && \
    cd /var/local/git/grpc-de195d3520a803fd6424bc33f154ba265af98bbe && \
    rm -rf cmake/build && mkdir cmake/build && cd cmake/build && cmake ../.. -DgRPC_INSTALL=ON && \
    cd /var/local/git/grpc-de195d3520a803fd6424bc33f154ba265af98bbe && \
    make && make install && ldconfig
