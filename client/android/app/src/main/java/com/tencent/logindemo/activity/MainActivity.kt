package com.tencent.logindemo.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tencent.logindemo.R
import com.tencent.logindemo.djinni.CommonReponse
import com.tencent.logindemo.djinni.HelloWorld
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    companion object {

        val TAG = "MainActivity"
        val HOST = "111.229.210.33" //腾讯云服务器IP地址
        val PORT = 50051 //grpc server监听端口

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

        device = android.provider.Settings.System.getString(
            contentResolver,
            android.provider.Settings.Secure.ANDROID_ID
        )
        showDevice(device)

        token = getPreferences(Context.MODE_PRIVATE).getString("token", null)
        showToken(token)

        refreshToken()
    }

    /**
     * 刷新token
     */
    private fun refreshToken() {
        if (token == null) {
            Log.i(TAG, "refreshToken, token is null")
        } else {
            Log.i(TAG, "refreshToken, token:${token}")

            lifecycleScope.launch {
                val response = refreshTokenInBackground(token)
                Log.i(TAG, "refreshToken, response:${response}")
                if (response.code == 0) {
                    Log.i(TAG, "refresh token success, token:${token}")
                    token = response.data
                    saveToke(token)
                    showToken(token)
                } else {
                    Log.e(TAG, "refresh token fail(${response.code}, ${response.msg})")
                }
            }
        }
    }

    suspend fun refreshTokenInBackground(token: String?): CommonReponse {
        return withContext(Dispatchers.Default) {
            service.refreshToken(token)
        }
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
                Log.i(TAG, "signup, name:${name}, password:${password}, device:${device}")

                lifecycleScope.launch {
                    val response = signupInBackground(name, password, device)
                    Log.i(TAG, "signup, response:${response}")
                    if (response.code == 0) {
                        Log.e(TAG, "signup success(${response.code}, ${response.msg})")
                        Toast.makeText(applicationContext, "注册成功", Toast.LENGTH_SHORT).show()
                        token = response.data
                        saveToke(token)
                        showToken(token)
                    } else {
                        Log.e(TAG, "signup fail(${response.code}, ${response.msg})")
                        Toast.makeText(
                            applicationContext,
                            "注册失败(${response.code}, ${response.msg})",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(applicationContext, "请输入合法的账号密码", Toast.LENGTH_SHORT).show()
            }
        }
        builder.show()
    }

    suspend fun signupInBackground(
        name: String?,
        password: String?,
        device: String?
    ): CommonReponse {
        return withContext(Dispatchers.Default) {
            service.signup(name, password, device)
        }
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
                Log.i(TAG, "login, name:${name}, password:${password}, device:${device}")

                lifecycleScope.launch {
                    val response = loginInBackground(name, password, device)
                    Log.i(TAG, "login, response:${response}")
                    if (response.code == 0) {
                        Log.e(TAG, "login success(${response.code}, ${response.msg})")
                        Toast.makeText(applicationContext, "登录成功", Toast.LENGTH_SHORT).show()
                        token = response.data
                        saveToke(token)
                        showToken(token)
                    } else {
                        Log.e(TAG, "login fail(${response.code}, ${response.msg})")
                        Toast.makeText(
                            applicationContext,
                            "登录失败(${response.code}, ${response.msg})",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(applicationContext, "请输入合法的账号密码", Toast.LENGTH_SHORT).show()
            }
        }
        builder.show()
    }

    suspend fun loginInBackground(
        name: String?,
        password: String?,
        device: String?
    ): CommonReponse {
        return withContext(Dispatchers.Default) {
            service.login(name, password, device)
        }
    }

    /**
     * 登出
     */
    fun logout(view: View) {
        if (null == token) {
            Toast.makeText(applicationContext, "还未登录", Toast.LENGTH_SHORT).show()
        } else {
            Log.i(TAG, "logout, token:${token}")

            lifecycleScope.launch {
                val response = logoutInBackground(token)
                Log.i(TAG, "logout, response:${response}")
                if (response.code == 0) {
                    Log.e(TAG, "logout success(${response.code}, ${response.msg})")
                    Toast.makeText(applicationContext, "登出成功", Toast.LENGTH_SHORT).show()
                    token = null
                    clearToken(token)
                    showToken(token)
                } else {
                    Log.e(TAG, "logout fail(${response.code}, ${response.msg})")
                    Toast.makeText(
                        applicationContext,
                        "登出失败(${response.code}, ${response.msg})",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    suspend fun logoutInBackground(token: String?): CommonReponse {
        return withContext(Dispatchers.Default) {
            service.logout(token)
        }
    }

    /**
     * 检查用户输入的账号和密码
     * TODO：实际情况下这里要做得很具体，限制字符串长度和字符组合，例如密码需要同时有大小写字母和数字等
     */
    private fun validNamePassword(name: String?, password: String?): Boolean {
        if (name.isNullOrEmpty() || password.isNullOrEmpty()) {
            return false
        }
        return true
    }

    /**
     * 保存数据
     * TODO：暂时只是保存到SharedPreference中，实际需要特别地以加密形式保存到本地
     */
    private fun saveToke(token: String?) {
        Log.i(TAG, "saveToken, token:${token}")
        getPreferences(Context.MODE_PRIVATE).edit().putString("token", token).apply()
    }

    private fun clearToken(token: String?) {
        Log.i(TAG, "clearToken, token:${token}")
        getPreferences(Context.MODE_PRIVATE).edit().remove("token").apply()
    }

    /**
     * 测试服务器端是否能正常通信
     */
    fun helloworld(view: View) {
        val message = service.sayHello("javayhu")
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun showDevice(device: String?) {
        deviceText.text = "device:${device}"
    }

    @SuppressLint("SetTextI18n")
    private fun showToken(token: String?) {
        tokenText.text = "token:${token}"
    }
}
