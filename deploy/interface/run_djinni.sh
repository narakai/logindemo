
#! /usr/bin/env bash

### Configuration

# Djinni IDL file location
djinni_file="logindemo.djinni"

# C++ namespace for generated src
namespace="logindemo"

# Java package name for generated src
java_package="com.tencent.logindemo.djinni"

# get base directory
base_dir=$(cd "`dirname "0"`" && pwd)

# get java directory from package name
java_dir=$(echo $java_package | tr . /)

# output directories for generated src
cpp_out="$base_dir/../client/android/app/src/main/cpp/djinni"
jni_out="$base_dir/../client/android/app/src/main/cpp/djinni"
java_out="$base_dir/../client/android/app/src/main/java/$java_dir"

# clean generated src dirs
rm -rf $cpp_out
rm -rf $jni_out
rm -rf $java_out 

# execute the djinni command
../tools/djinni/src/run \
   --java-out $java_out \
   --java-package $java_package \
   --ident-java-field mFooBar \
   --cpp-out $cpp_out \
   --cpp-namespace $namespace \
   --jni-out $jni_out \
   --ident-jni-class NativeFooBar \
   --ident-jni-file NativeFooBar \
   --idl $djinni_file
