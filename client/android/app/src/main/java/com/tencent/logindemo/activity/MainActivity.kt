package com.tencent.logindemo.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tencent.logindemo.R
import com.tencent.logindemo.djinni.HelloWorld
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {

        val TAG = "MainActivity"

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun testGRPC(view: View) {
        val helloWorld = HelloWorld.create()
        val message = helloWorld.sayHello("111.229.210.33", 50051, "logindemo")
        sample_text.text = message
    }
}
