FROM grpc/cxx:1.12.0

WORKDIR /data

COPY . .

### 安装bazel
RUN curl -OL http://video.pocketpoetry.club/bazel-3.1.0-installer-linux-x86_64.sh
RUN chmod +x bazel-3.1.0-installer-linux-x86_64.sh && \
    ./bazel-3.1.0-installer-linux-x86_64.sh --user

### 编译目标server
RUN /root/bin/bazel build src:greeter-server

### 启动目标server
CMD ["./greeter-server"]
