package com.tencent.logindemo.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tencent.logindemo.R
import com.tencent.logindemo.djinni.HelloWorld
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        //sample_text.text = stringFromJNI()
        val helloWorld = HelloWorld.create()
        sample_text.text = helloWorld.helloWorld
    }

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("grpc-helloworld")
        }
    }

    external fun sayHello(
        host: String?,
        port: Int,
        message: String?
    ): String?

    fun testGRPC(view: View) {
        //startActivity(Intent(this, HelloworldActivity::class.java))
        val message = sayHello("111.229.210.33", 50051, "logindemo")
        sample_text.text = message
    }
}
