// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: helloworld.proto

package com.tencent.logindemo.proto;

public final class HelloWorldProto {
  private HelloWorldProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_LoginRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_LoginRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_LoginResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_LoginResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_TokenInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_TokenInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_HelloRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_HelloRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_HelloReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_HelloReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020helloworld.proto\022\nhelloworld\">\n\014LoginR" +
      "equest\022\014\n\004name\030\001 \001(\t\022\020\n\010password\030\002 \001(\t\022\016" +
      "\n\006device\030\003 \001(\t\"X\n\rLoginResponse\022\014\n\004code\030" +
      "\001 \001(\005\022\017\n\007message\030\002 \001(\t\022(\n\ttokenInfo\030\003 \001(" +
      "\0132\025.helloworld.TokenInfo\"\032\n\tTokenInfo\022\r\n" +
      "\005token\030\001 \001(\t\"\034\n\014HelloRequest\022\014\n\004name\030\001 \001" +
      "(\t\"\035\n\nHelloReply\022\017\n\007message\030\001 \001(\t2\314\002\n\007Gr" +
      "eeter\022>\n\010SayHello\022\030.helloworld.HelloRequ" +
      "est\032\026.helloworld.HelloReply\"\000\022?\n\006Signup\022" +
      "\030.helloworld.LoginRequest\032\031.helloworld.L" +
      "oginResponse\"\000\022>\n\005Login\022\030.helloworld.Log" +
      "inRequest\032\031.helloworld.LoginResponse\"\000\022<" +
      "\n\006Logout\022\025.helloworld.TokenInfo\032\031.hellow" +
      "orld.LoginResponse\"\000\022B\n\014RefreshToken\022\025.h" +
      "elloworld.TokenInfo\032\031.helloworld.LoginRe" +
      "sponse\"\000B0\n\033com.tencent.logindemo.protoB" +
      "\017HelloWorldProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_helloworld_LoginRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_helloworld_LoginRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_LoginRequest_descriptor,
        new java.lang.String[] { "Name", "Password", "Device", });
    internal_static_helloworld_LoginResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_helloworld_LoginResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_LoginResponse_descriptor,
        new java.lang.String[] { "Code", "Message", "TokenInfo", });
    internal_static_helloworld_TokenInfo_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_helloworld_TokenInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_TokenInfo_descriptor,
        new java.lang.String[] { "Token", });
    internal_static_helloworld_HelloRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_helloworld_HelloRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_HelloRequest_descriptor,
        new java.lang.String[] { "Name", });
    internal_static_helloworld_HelloReply_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_helloworld_HelloReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_HelloReply_descriptor,
        new java.lang.String[] { "Message", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
