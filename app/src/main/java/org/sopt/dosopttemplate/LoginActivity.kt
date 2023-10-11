package org.sopt.dosopttemplate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signbutton = binding.signupButton
        val loginbutton = binding.loginButton

        signbutton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginbutton.setOnClickListener {
                val receivedIntent = getIntent()
                    val enteredId = receivedIntent.getStringExtra("entered_id")
                    val enteredPassword = receivedIntent.getStringExtra("entered_password")
                    val enteredMajor = receivedIntent.getStringExtra("entered_Major")
                    val enteredName = receivedIntent.getStringExtra("entered_Name")

                    Log.d("LoginActivity", "enteredId: $enteredId")
                    Log.d("LoginActivity", "enteredPassword: $enteredPassword")
                    Log.d("LoginActivity", "enteredMajor: $enteredMajor")
                    Log.d("LoginActivity", "enteredName: $enteredName")

                val inputId = binding.inputTextId
                val inputPw = binding.inputTextPw

                if (enteredId != null && enteredPassword != null && enteredMajor != null && enteredName != null) {
                    val isLoginSuccessful =
                        enteredId == inputId.text.toString() && enteredPassword == inputPw.text.toString()
                    if (isLoginSuccessful) {
                        Toast.makeText(this, "로그인에 성공했습니다 :)", Toast.LENGTH_LONG).show()

                        val mainintent = Intent(this, MainActivity::class.java)
                        mainintent.putExtra("user_id", enteredId)
                        mainintent.putExtra("user_major", enteredMajor)
                        mainintent.putExtra("user_name", enteredName)

                        startActivity(mainintent)
                    } else {
                        Toast.makeText(this, "로그인에 실패했습니다 :(", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

