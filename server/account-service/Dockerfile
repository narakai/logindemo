FROM grpc/cxx:1.12.0

WORKDIR /data

COPY . .

RUN bazel build src:greeter-server

CMD ["./greeter-server"]
