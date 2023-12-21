package org.sopt.dosopttemplate.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.Home.HomeActivity
import org.sopt.dosopttemplate.signup.SignUpActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        signup()
        login()
        observeLoginResult()
    }

    private fun login() {
        binding.loginButton.setOnClickListener {
            val id = binding.inputTextId.text.toString()
            val password = binding.inputTextPw.text.toString()

            loginViewModel.login(
                id = id,
                password = password,)}}

    private fun signup(){
        binding.signupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }}

    private fun observeLoginResult() {
        loginViewModel.loginSuccess.observe(this) {
            if (it) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java,
                    ),
                )
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }}



