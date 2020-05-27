// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from logindemo.djinni

#include "NativeHelloWorld.hpp"  // my header
#include "Marshal.hpp"
#include "NativeCommonReponse.hpp"

namespace djinni_generated {

NativeHelloWorld::NativeHelloWorld() : ::djinni::JniInterface<::logindemo::HelloWorld, NativeHelloWorld>("com/tencent/logindemo/djinni/HelloWorld$CppProxy") {}

NativeHelloWorld::~NativeHelloWorld() = default;


CJNIEXPORT void JNICALL Java_com_tencent_logindemo_djinni_HelloWorld_00024CppProxy_nativeDestroy(JNIEnv* jniEnv, jobject /*this*/, jlong nativeRef)
{
    try {
        DJINNI_FUNCTION_PROLOGUE1(jniEnv, nativeRef);
        delete reinterpret_cast<::djinni::CppProxyHandle<::logindemo::HelloWorld>*>(nativeRef);
    } JNI_TRANSLATE_EXCEPTIONS_RETURN(jniEnv, )
}

CJNIEXPORT jobject JNICALL Java_com_tencent_logindemo_djinni_HelloWorld_00024CppProxy_create(JNIEnv* jniEnv, jobject /*this*/, jstring j_host, jint j_port)
{
    try {
        DJINNI_FUNCTION_PROLOGUE0(jniEnv);
        auto r = ::logindemo::HelloWorld::create(::djinni::String::toCpp(jniEnv, j_host),
                                                 ::djinni::I32::toCpp(jniEnv, j_port));
        return ::djinni::release(::djinni_generated::NativeHelloWorld::fromCpp(jniEnv, r));
    } JNI_TRANSLATE_EXCEPTIONS_RETURN(jniEnv, 0 /* value doesn't matter */)
}

CJNIEXPORT jobject JNICALL Java_com_tencent_logindemo_djinni_HelloWorld_00024CppProxy_native_1signup(JNIEnv* jniEnv, jobject /*this*/, jlong nativeRef, jstring j_name, jstring j_password, jstring j_device)
{
    try {
        DJINNI_FUNCTION_PROLOGUE1(jniEnv, nativeRef);
        const auto& ref = ::djinni::objectFromHandleAddress<::logindemo::HelloWorld>(nativeRef);
        auto r = ref->signup(::djinni::String::toCpp(jniEnv, j_name),
                             ::djinni::String::toCpp(jniEnv, j_password),
                             ::djinni::String::toCpp(jniEnv, j_device));
        return ::djinni::release(::djinni_generated::NativeCommonReponse::fromCpp(jniEnv, r));
    } JNI_TRANSLATE_EXCEPTIONS_RETURN(jniEnv, 0 /* value doesn't matter */)
}

CJNIEXPORT jobject JNICALL Java_com_tencent_logindemo_djinni_HelloWorld_00024CppProxy_native_1login(JNIEnv* jniEnv, jobject /*this*/, jlong nativeRef, jstring j_name, jstring j_password, jstring j_device)
{
    try {
        DJINNI_FUNCTION_PROLOGUE1(jniEnv, nativeRef);
        const auto& ref = ::djinni::objectFromHandleAddress<::logindemo::HelloWorld>(nativeRef);
        auto r = ref->login(::djinni::String::toCpp(jniEnv, j_name),
                            ::djinni::String::toCpp(jniEnv, j_password),
                            ::djinni::String::toCpp(jniEnv, j_device));
        return ::djinni::release(::djinni_generated::NativeCommonReponse::fromCpp(jniEnv, r));
    } JNI_TRANSLATE_EXCEPTIONS_RETURN(jniEnv, 0 /* value doesn't matter */)
}

CJNIEXPORT jobject JNICALL Java_com_tencent_logindemo_djinni_HelloWorld_00024CppProxy_native_1logout(JNIEnv* jniEnv, jobject /*this*/, jlong nativeRef, jstring j_token)
{
    try {
        DJINNI_FUNCTION_PROLOGUE1(jniEnv, nativeRef);
        const auto& ref = ::djinni::objectFromHandleAddress<::logindemo::HelloWorld>(nativeRef);
        auto r = ref->logout(::djinni::String::toCpp(jniEnv, j_token));
        return ::djinni::release(::djinni_generated::NativeCommonReponse::fromCpp(jniEnv, r));
    } JNI_TRANSLATE_EXCEPTIONS_RETURN(jniEnv, 0 /* value doesn't matter */)
}

CJNIEXPORT jstring JNICALL Java_com_tencent_logindemo_djinni_HelloWorld_00024CppProxy_native_1sayHello(JNIEnv* jniEnv, jobject /*this*/, jlong nativeRef, jstring j_msg)
{
    try {
        DJINNI_FUNCTION_PROLOGUE1(jniEnv, nativeRef);
        const auto& ref = ::djinni::objectFromHandleAddress<::logindemo::HelloWorld>(nativeRef);
        auto r = ref->sayHello(::djinni::String::toCpp(jniEnv, j_msg));
        return ::djinni::release(::djinni::String::fromCpp(jniEnv, r));
    } JNI_TRANSLATE_EXCEPTIONS_RETURN(jniEnv, 0 /* value doesn't matter */)
}

}  // namespace djinni_generated
