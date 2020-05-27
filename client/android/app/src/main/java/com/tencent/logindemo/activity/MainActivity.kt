package com.tencent.logindemo.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tencent.logindemo.R
import com.tencent.logindemo.djinni.HelloWorld
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {

        val TAG = "MainActivity"
        val HOST = "111.229.210.33"
        val PORT = 50051

        init {
            System.loadLibrary("native-lib")
        }
    }

    /**
     * 远程服务
     */
    lateinit var service: HelloWorld

    /**
     * 1、用户登录成功之后服务器返回给客户端的凭证token
     * 2、当检测到token与服务器端保存的不一致时我们需要让用户重新登录
     * 3、TODO：token可以设计的更加复杂，声明token有效期，客户端检测到token有效期过了的话也需要让用户重新登录
     */
    var token: String? = null

    /**
     * 1、设备id，用于标识某个设备
     * 2、TODO：设备id跟当前设备的很多信息有关，例如机型、系统版本、序列号等等，这里直接使用ANDROID_ID标识
     */
    lateinit var device: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = HelloWorld.create(HOST, PORT)
        device = android.provider.Settings.System.getString(contentResolver, android.provider.Settings.Secure.ANDROID_ID)
        showDevice(device)
        refreshUser()
        refreshToken()
    }

    private fun refreshUser() {
        val userName = getPreferences(Context.MODE_PRIVATE).getString("userName", null)
        showUserName(userName)
    }

    private fun refreshToken() {
        token = getPreferences(Context.MODE_PRIVATE).getString("token", null)
        showToken(token)

        if (token != null) {
            //service.refreshToken(token)
        }
    }

    private fun saveToke(token: String?) {
        Log.i(TAG, "saveToken, token:${token}")
        getPreferences(Context.MODE_PRIVATE).edit().putString("token", token).apply()
    }

    private fun saveUserName(userName: String?) {
        Log.i(TAG, "saveUserName, userName:${userName}")
        getPreferences(Context.MODE_PRIVATE).edit().putString("userName", userName).apply()
    }

    /**
     * 注册
     */
    fun signup(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("注册")
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_name_password, null)
        val nameText = dialogLayout.findViewById<EditText>(R.id.nameText)
        val passwordText = dialogLayout.findViewById<EditText>(R.id.passwordText)
        builder.setView(dialogLayout)
        builder.setPositiveButton("注册") { dialogInterface, which ->
            val name = nameText.text.toString()
            val password = passwordText.text.toString()
            if (validNamePassword(name, password)) {
                //service.signup(name, password, device)
                Log.i(TAG, "signup, name:${name}, password:${password}")
            } else {
                Toast.makeText(applicationContext, "请输入合法的账号密码", Toast.LENGTH_SHORT).show()
            }
        }
        builder.show()
    }

    private fun validNamePassword(name: String?, password: String?): Boolean {
        if (name.isNullOrEmpty() || password.isNullOrEmpty()) {
            return false
        }
        return true
    }

    /**
     * 登录
     */
    fun login(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("登录")
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_name_password, null)
        val nameText = dialogLayout.findViewById<EditText>(R.id.nameText)
        val passwordText = dialogLayout.findViewById<EditText>(R.id.passwordText)
        builder.setView(dialogLayout)
        builder.setPositiveButton("登录") { dialogInterface, which ->
            val name = nameText.text.toString()
            val password = passwordText.text.toString()
            if (validNamePassword(name, password)) {
                //service.login(name, password, device)
                Log.i(TAG, "login, name:${name}, password:${password}")
            } else {
                Toast.makeText(applicationContext, "请输入合法的账号密码", Toast.LENGTH_SHORT).show()
            }
        }
        builder.show()
    }

    /**
     * 登出
     */
    fun logout(view: View) {
        if (null == token) {
            Toast.makeText(applicationContext, "还未登录", Toast.LENGTH_SHORT).show()
        } else {
            //service.logout(token)
        }
    }

    /**
     * 测试服务器端是否能正常通信
     */
    fun helloworld(view: View) {
        val message = service.sayHello("World from Server")
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun showUserName(userName:String?) {
        userText.text = "user:${userName}"
    }

    @SuppressLint("SetTextI18n")
    private fun showDevice(device:String?) {
        deviceText.text = "device:${device}"
    }

    @SuppressLint("SetTextI18n")
    private fun showToken(token:String?) {
        tokenText.text = "token:${token}"
    }
}
