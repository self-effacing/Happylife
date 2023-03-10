package com.example.a111111

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.a111111.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class WT_LoginActivity : WT_BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val account = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (account.isNotEmpty() && password.isNotEmpty()) {
                GlobalScope.launch(Dispatchers.IO) {
                    var conn: Connection? = null
                    var statement: PreparedStatement? = null
                    var resultSet: ResultSet? = null

                    try {
                        // Connect to MySQL database
                        conn = DriverManager.getConnection(
                            "jdbc:mysql://39.101.79.219:3306/sgly2004?useSSL=false",
                            "sgly2004",
                            "sgly2004"
                        )

                        // Prepare SQL statement to retrieve user data
                        statement = conn.prepareStatement(
                            "SELECT * FROM users WHERE username = ? AND password = ?"
                        )
                        statement.setString(1, account)
                        statement.setString(2, password)

                        // Execute SQL statement
                        resultSet = statement.executeQuery()

                        if (resultSet.next()) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(this@WT_LoginActivity, "????????????", Toast.LENGTH_SHORT).show()
                            }
                            conn.close()

                            // ???????????? WT_User ????????????????????????????????????????????????
                            val user = WT_User(account, password)

                            val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
                            sharedPreferences.edit()
                                .putString("username", user.username)
                                .putString("password", user.password)
                                .apply()

                            // ????????????????????? WT_Application ??? currentUser ?????????
                            WT_Application.currentUser = user

                            withContext(Dispatchers.Main) {
                                val intent = Intent(this@WT_LoginActivity, WT_Switch11Activity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(this@WT_LoginActivity, "????????????????????????", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: SQLException) {
                        e.printStackTrace()
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@WT_LoginActivity, "???????????????????????????", Toast.LENGTH_SHORT).show()
                        }
                    } finally {
                        resultSet?.close()
                        statement?.close()
                        conn?.close()
                    }
                }
            } else {
                Toast.makeText(this, "???????????????????????????", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnZhu.setOnClickListener{
            val intent = Intent(this, WT_RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
