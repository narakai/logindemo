package com.tencent.logindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    fun testGRPC(view: View) {
        startActivity(Intent(this, HelloworldActivity::class.java))
    }
}
