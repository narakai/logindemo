// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from logindemo.djinni

package com.tencent.logindemo.djinni;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class HelloWorld {
    public abstract String getHelloWorld();

    public abstract String sayHello(String host, int port, String msg);

    public static HelloWorld create()
    {
        return CppProxy.create();
    }

    private static final class CppProxy extends HelloWorld
    {
        private final long nativeRef;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);

        private CppProxy(long nativeRef)
        {
            if (nativeRef == 0) throw new RuntimeException("nativeRef is zero");
            this.nativeRef = nativeRef;
        }

        private native void nativeDestroy(long nativeRef);
        public void _djinni_private_destroy()
        {
            boolean destroyed = this.destroyed.getAndSet(true);
            if (!destroyed) nativeDestroy(this.nativeRef);
        }
        protected void finalize() throws java.lang.Throwable
        {
            _djinni_private_destroy();
            super.finalize();
        }

        @Override
        public String getHelloWorld()
        {
            assert !this.destroyed.get() : "trying to use a destroyed object";
            return native_getHelloWorld(this.nativeRef);
        }
        private native String native_getHelloWorld(long _nativeRef);

        @Override
        public String sayHello(String host, int port, String msg)
        {
            assert !this.destroyed.get() : "trying to use a destroyed object";
            return native_sayHello(this.nativeRef, host, port, msg);
        }
        private native String native_sayHello(long _nativeRef, String host, int port, String msg);

        public static native HelloWorld create();
    }
}
