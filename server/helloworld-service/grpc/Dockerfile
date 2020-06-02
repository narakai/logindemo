FROM ubuntu:18.04

COPY . .

RUN mv /etc/apt/sources.list /etc/apt/sources.list.bak && mv sources.list /etc/apt/

RUN apt-get update -y && apt-get install -y default-jdk

RUN ./gradlew installDist && ./build/install/helloworld-service/bin/hello-world-server
