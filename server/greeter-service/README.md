ubuntu@VM-0-4-ubuntu:~/newgrpc$ docker build -t greeter-server -f server/Dockerfile .
Sending build context to Docker daemon  15.87kB
Step 1/7 : FROM grpc/cxx:1.12.0
1.12.0: Pulling from grpc/cxx
05d1a5232b46: Already exists 
c0608c0cdfa8: Pull complete 
e33b0d005dc7: Pull complete 
Digest: sha256:33cb4570db8f2f18f22ef47a2f06c905804c961cd5465eec48ce491ac45a84c7
Status: Downloaded newer image for grpc/cxx:1.12.0
 ---> 5a22af41ed72
Step 2/7 : WORKDIR /data
 ---> Running in f58247a379e5
Removing intermediate container f58247a379e5
 ---> 172fb60e87d1
Step 3/7 : COPY server/server.cc ./server/
 ---> ad396f6fe51b
Step 4/7 : COPY proto/helloworld.proto ./proto/
 ---> 291377c30f82
Step 5/7 : COPY Makefile ./
 ---> 537282a8da9a
Step 6/7 : RUN make greeter-server && make clean
 ---> Running in 6497de97de95
protoc -I ./proto/ --cpp_out=. ./proto/helloworld.proto
g++ -std=c++11 -I . `pkg-config --cflags protobuf grpc`  -c -o helloworld.pb.o helloworld.pb.cc
protoc -I ./proto/ --grpc_out=. --plugin=protoc-gen-grpc=`which grpc_cpp_plugin` ./proto/helloworld.proto
g++ -std=c++11 -I . `pkg-config --cflags protobuf grpc`  -c -o helloworld.grpc.pb.o helloworld.grpc.pb.cc
g++ -std=c++11 -I . `pkg-config --cflags protobuf grpc`  -c -o server/server.o server/server.cc
g++ helloworld.pb.o helloworld.grpc.pb.o server/server.o -L/usr/local/lib `pkg-config --libs protobuf grpc++ grpc` -Wl,--no-as-needed -lgrpc++_reflection -Wl,--as-needed -ldl -o greeter-server
rm -f *.o client/*.o server/*.o *.pb *.pb.cc *.pb.h
Removing intermediate container 6497de97de95
 ---> fefc84ff0f8d
Step 7/7 : CMD ["./greeter-server"]
 ---> Running in b5408d3f7217
Removing intermediate container b5408d3f7217
 ---> e72ee1fc3253
Successfully built e72ee1fc3253
Successfully tagged greeter-server:latest
ubuntu@VM-0-4-ubuntu:~/newgrpc
ubuntu@VM-0-4-ubuntu:~/newgrpc$ docker build -t greeter-client -f client/Dockerfile .
Sending build context to Docker daemon  15.87kB
Step 1/7 : FROM grpc/cxx:1.12.0
 ---> 5a22af41ed72
Step 2/7 : WORKDIR /data
 ---> Using cache
 ---> 172fb60e87d1
Step 3/7 : COPY client/client.cc ./client/
 ---> 295c152e431b
Step 4/7 : COPY proto/helloworld.proto ./proto/
 ---> bef0925944e0
Step 5/7 : COPY Makefile ./
 ---> 29fd8241edbb
Step 6/7 : RUN make greeter-client && make clean
 ---> Running in bfa2d5476837
protoc -I ./proto/ --cpp_out=. ./proto/helloworld.proto
g++ -std=c++11 -I . `pkg-config --cflags protobuf grpc`  -c -o helloworld.pb.o helloworld.pb.cc
protoc -I ./proto/ --grpc_out=. --plugin=protoc-gen-grpc=`which grpc_cpp_plugin` ./proto/helloworld.proto
g++ -std=c++11 -I . `pkg-config --cflags protobuf grpc`  -c -o helloworld.grpc.pb.o helloworld.grpc.pb.cc
g++ -std=c++11 -I . `pkg-config --cflags protobuf grpc`  -c -o client/client.o client/client.cc
g++ helloworld.pb.o helloworld.grpc.pb.o client/client.o -L/usr/local/lib `pkg-config --libs protobuf grpc++ grpc` -Wl,--no-as-needed -lgrpc++_reflection -Wl,--as-needed -ldl -o greeter-client
rm -f *.o client/*.o server/*.o *.pb *.pb.cc *.pb.h
Removing intermediate container bfa2d5476837
 ---> 69d36a40c942
Step 7/7 : CMD ["./greeter-client"]
 ---> Running in 01825c5d2fa9
Removing intermediate container 01825c5d2fa9
 ---> 399d32d23d88
Successfully built 399d32d23d88
Successfully tagged greeter-client:latest
ubuntu@VM-0-4-ubuntu:~/newgrpc$

ubuntu@VM-0-4-ubuntu:~/newgrpc$ docker run --rm -p 50051:50051 --name greeter-server greeter-server
Server listening on 0.0.0.0:50051
Received request: name: "world"

ubuntu@VM-0-4-ubuntu:~/newgrpc$ docker run --rm --network=host greeter-client ./greeter-client localhost
Sending request to localhost:50051 ...
Greeter received: Hello world


$
